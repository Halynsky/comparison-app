package com.improveusa.util;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class BranchesCounter {

    public static final String JSON_PATH = "src/main/resources/parsers/porch/result/catalog.json";

    public static int maxChainQuestions;

    public static void main(String[] args) throws FileNotFoundException {

        int servicesCounter = 0;
        int questionsCounter = 0;
        int questionChainLength = 0;
        int maxQuestions = 0;

        JsonParser jsonParser = new JsonParser();

        BufferedReader br = new BufferedReader(new FileReader(JSON_PATH));
        JsonArray mainArray = jsonParser.parse(br).getAsJsonArray();

        for(JsonElement service : mainArray) {
            servicesCounter++;

            JsonObject questionaryObject = service.getAsJsonObject().get("questionary").getAsJsonObject();

            if (questionaryObject.get("questions") == null)
                continue;

            JsonObject questionsObject = questionaryObject.get("questions").getAsJsonObject();

            questionsCounter = 0;

            for (Map.Entry<String,JsonElement> entry: questionsObject.entrySet()) {
                questionsCounter++;
            }

            questionChainLength = 1;
            String startQuestionName = questionaryObject.get("startQuestionName").getAsString();
            JsonObject startQuestionObject = questionsObject.get(startQuestionName).getAsJsonObject();

            chainQuestion(questionsObject, startQuestionObject, questionChainLength);

            if (questionsCounter > maxQuestions)
                maxQuestions = questionsCounter;

        }

        System.out.println("Services = " + servicesCounter);
        System.out.println("MaxQuestions = " + maxQuestions);
        System.out.println("maxChainQuestions = " + maxChainQuestions);

    }

    public static void chainQuestion(JsonObject questionsObject, JsonObject parentQuestionObject, int questionChainLength){

//        System.out.println("START");
//        System.out.println(parentQuestionObject);

        JsonElement conditionsElement = parentQuestionObject.get("conditions");

        if (conditionsElement != null) {

            for (JsonElement condition: parentQuestionObject.get("conditions").getAsJsonArray()) {

                questionChainLength++;
                String nextQuestionName = condition.getAsJsonObject().get("nextQuestionName").getAsString();
//                System.out.println("nextQuestionName = " + nextQuestionName);
                JsonObject nextQuestionObject = questionsObject.get(nextQuestionName).getAsJsonObject();
                chainQuestion(questionsObject, nextQuestionObject, questionChainLength);

            }

        } else  {
            questionChainLength++;
            JsonElement nextQuestionNameElement = parentQuestionObject.get("nextQuestionName");

            if (nextQuestionNameElement == null) {
//                System.out.println("questionChainLength = " + questionChainLength);
                if (maxChainQuestions < questionChainLength)
                    maxChainQuestions = questionChainLength;
                return;
            }


            String nextQuestionName = nextQuestionNameElement.getAsString();
//            System.out.println("nextQuestionName = " + nextQuestionName);
            JsonObject nextQuestionObject = questionsObject.get(nextQuestionName).getAsJsonObject();
            chainQuestion(questionsObject, nextQuestionObject, questionChainLength);
        }

    }


}
