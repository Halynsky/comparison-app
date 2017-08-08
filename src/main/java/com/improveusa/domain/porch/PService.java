package com.improveusa.domain.porch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by msoltys on 4/22/2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PService {

    int id;
    String name;
    PQuestionary questionary;
}

