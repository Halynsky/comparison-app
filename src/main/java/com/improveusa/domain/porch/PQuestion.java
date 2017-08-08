package com.improveusa.domain.porch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by msoltys on 4/22/2017.
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PQuestion {

    int id;
    String name;
    String description;
    String type;
    List<PAnswer> answers;
}
