package com.improveusa.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.improveusa.domain.porch.PAnswer;
import com.improveusa.domain.porch.PQuestion;
import com.improveusa.domain.porch.PQuestionary;

import java.io.IOException;
import java.util.*;

/**
 * Created by msoltys on 4/23/2017.
 */
public class QestionaryDeserializer extends StdDeserializer<PQuestionary> {

    public QestionaryDeserializer() {
        this(null);
    }

    public QestionaryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PQuestionary deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (!node.hasNonNull("id")) {
            return null;
        }
        return new PQuestionary()
                .setId(node.get("id").numberValue().intValue())
                .setQuestions(parseQuestions(node.get("questions")));
    }

    // TODO : change this
    private List<PQuestion> parseQuestions(JsonNode questionsNode) {
        Set<String> duplicates = new HashSet<>();
        List<PQuestion> questions = new ArrayList<>();
        questionsNode.forEach(jsonNode -> {
            PQuestion question = new PQuestion()
                    .setId(jsonNode.get("id").numberValue().intValue())
                    .setName(jsonNode.get("name").textValue())
                    .setDescription(jsonNode.get("description").textValue())
                    .setType(jsonNode.get("type").textValue())
                    .setAnswers(parseAnswers(jsonNode.get("answers")));
            if(!duplicates.contains(question.getDescription())) {
                questions.add(question);
                duplicates.add(question.getDescription());
            }
        });
        return  questions;
    }

    private List<PAnswer> parseAnswers(JsonNode answersNode) {
        List<PAnswer> answers = new ArrayList<>();
        answersNode.forEach(jsonNode -> {
            PAnswer answer = new PAnswer()
                    .setId(jsonNode.get("id").numberValue().intValue())
                    .setName(jsonNode.get("name").textValue())
                    .setDescription(jsonNode.get("description").textValue());
            answers.add(answer);
        });
        return answers;
    }
}
