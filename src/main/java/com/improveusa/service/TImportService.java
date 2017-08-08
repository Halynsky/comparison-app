package com.improveusa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.improveusa.domain.thumbtack.TCategory;
import com.improveusa.domain.thumbtack.TQuestion;
import com.improveusa.domain.thumbtack.TService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class TImportService {

    public List<TCategory> importCategories(InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<TCategory> importedEntries = mapper.readValue(in, new TypeReference<List<TCategory>>() {});
        defineIds(importedEntries);
        return importedEntries;
    }

    private void defineIds(List<TCategory> categories) {
        int catId = 0;
        int serId = 0;
        for (TCategory category : categories) {
            category.setId(catId++);
            for (TService service : category.getServices()) {
                service.setId(serId++);
            }
        }
    }

    public List<TQuestion> importDefaultQuestions(InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<TQuestion> questions = mapper.readValue(in, new TypeReference<List<TQuestion>>() {});
        questions.forEach(tQuestion -> tQuestion.setDefault(true));
        return questions;
    }
}
