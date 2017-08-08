package com.improveusa.domain.thumbtack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by msoltys on 4/24/2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TService {
    int id;
    String name;
    List<TQuestion> questions;


}
