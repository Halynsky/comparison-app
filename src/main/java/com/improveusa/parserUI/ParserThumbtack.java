package com.improveusa.parserUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParserThumbtack {

    public static void main(String[] args) throws InterruptedException {

        parse();

    }

    public static void parse() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");

        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        WebDriver driver = new FirefoxDriver(profile);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);

        driver.get("https://www.thumbtack.com/home-improvement");

        List<WebElement> categories = driver.findElements(By.cssSelector(".MoreServices-service"));

//        System.out.println(Arrays.toString(categories.toArray()));

        JsonArray mainArray = new JsonArray();

        for (WebElement categoryElement: categories) {

            WebElement categoryHeader = categoryElement.findElement(By.cssSelector(".MoreServices-service-header"));

            JsonObject categoryObject = new JsonObject();
            categoryObject.addProperty("name", categoryHeader.getText());

            JsonArray servicesArray = new JsonArray();

            List<WebElement> services = categoryElement.findElements(By.cssSelector("ul li a"));

            for (WebElement servicesElement: services) {

                JsonObject serviceObject = new JsonObject();

                System.out.println(servicesElement.toString());
                System.out.println("Service name = " + servicesElement.getText());
                serviceObject.addProperty("name", servicesElement.getText());
                servicesArray.add(serviceObject);

                System.out.println("Clicking element with name => " + servicesElement.getText());
//                servicesElement.click();

                String originalHandle = driver.getWindowHandle();

                String selectLinkOpeningNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
                servicesElement.sendKeys(selectLinkOpeningNewTab);

                Thread.sleep(3000);

                System.out.println("windowHandles = " + Arrays.toString(driver.getWindowHandles().toArray()));
                driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);

//                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");

                JsonArray questionsArray = new JsonArray();

                List<WebElement> questions = driver.findElements(By.cssSelector("request-form-template > div > fieldset"));

                System.out.println("QUESTIONS SIZE = " + questions.size());

                for (WebElement questionElement: questions) {

                    JsonObject questionObject = new JsonObject();
                    questionObject.addProperty("description", questionElement.findElement(By.cssSelector(".RequestForm-sectionHeader > span > span")).getText());

                    List<WebElement> answers = questionElement.findElements(By.cssSelector("request-form-template .InputContainer label > div"));

                    JsonArray answersArray = new JsonArray();

                    for (WebElement answersElement: answers) {

                        JsonObject answerObject = new JsonObject();
                        answerObject.addProperty("description", answersElement.getText());

                        answersArray.add(answerObject);

                    }

                    questionObject.add("answers", answersArray);
                    questionsArray.add(questionObject);

                }

                serviceObject.add("questions", questionsArray);


                System.out.println("Clicking back");



                //Do something to open new tabs

                for(String handle : driver.getWindowHandles()) {
                    if (!handle.equals(originalHandle)) {
                        driver.switchTo().window(handle);
                        driver.close();
                    }
                }

                driver.switchTo().window(originalHandle);


//                driver.navigate().back();



            }

            categoryObject.add("services", servicesArray);

            mainArray.add(categoryObject);

        }

        System.out.println(mainArray.toString());

        driver.close();

    }

}
