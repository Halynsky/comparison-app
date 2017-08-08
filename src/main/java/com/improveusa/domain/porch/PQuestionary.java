package com.improveusa.domain.porch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.improveusa.service.QestionaryDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by msoltys on 4/22/2017.
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = QestionaryDeserializer.class)
public class PQuestionary {

    int id;
    String name;
    String description;
    boolean isDefault;
    List<PQuestion> questions;
    List<Condition> conditions;


    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {
        List<Dependent> dependents;
        String nextQuestionName;
    }

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dependent {
        String questionName;
        String answerName;
    }
}
