package com.improveusa.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Statistic {
    String vendor;
    int categoriesCount;
    int servicesCount;
    int questionCoverage;
    int maxQuestions;
    int minQuestions;
    int averageQuestions;
}
