package com.improveusa.domain.thumbtack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by msoltys on 4/24/2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TAnswer {
    int id;
    String description;
}
