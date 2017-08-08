package com.improveusa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.improveusa.domain.porch.PQuestion;
import com.improveusa.domain.porch.PService;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class PImportService {

    public List<PService> importServices(InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PService> importedEntries = mapper.readValue(in, new TypeReference<List<PService>>() {});
        return importedEntries;
    }

    public List<PQuestion> importDefaultQuestions(InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PQuestion> questions = mapper.readValue(in, new TypeReference<List<PQuestion>>() {});
        return questions;
    }

}
