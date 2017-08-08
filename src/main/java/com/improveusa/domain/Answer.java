package com.improveusa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by msoltys on 4/24/2017.
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {

    int id;
    String label;
    boolean collapsed = true;
}
