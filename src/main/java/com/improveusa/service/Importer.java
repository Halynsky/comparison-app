package com.improveusa.service;

import com.improveusa.domain.porch.PQuestion;
import com.improveusa.domain.porch.PQuestionary;
import com.improveusa.domain.porch.PService;
import com.improveusa.domain.thumbtack.TCategory;
import com.improveusa.domain.thumbtack.TQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Importer {

    @Autowired
    private ApplicationContext applicationContext;


    public List<PService> importPorch() throws URISyntaxException, IOException {
        PImportService pImportService = new PImportService();
        List<PQuestion> defaultQuest = pImportService.importDefaultQuestions(getInputStream("parsers/porch/result/defaultQuestions.json"));
        List<PService> services = pImportService.importServices(getInputStream("parsers/porch/result/catalog.json"));
        services.forEach(pService -> {
            if (pService.getQuestionary() == null) {
                pService.setQuestionary(new PQuestionary().setDescription("Default questions").setQuestions(defaultQuest).setDefault(true));
            }
        });
        return services;
    }

    public List<TCategory> importThumbtack() throws URISyntaxException, IOException {
        TImportService tImportService = new TImportService();
        List<TQuestion> generalQuestions = tImportService.importDefaultQuestions(getInputStream("parsers/thumbtack/result/generalQuestions.json"));
        List<TCategory> categories = tImportService.importCategories(getInputStream("parsers/thumbtack/result/catalog.json"));
        categories.forEach(tCategory -> {
            tCategory.getServices().forEach(tService -> {
                tService.getQuestions().addAll(generalQuestions);
            });
        });
        return categories;
    }

    private InputStream getInputStream(String path) throws IOException {
        URL url = getClass().getClassLoader().getResource(path);
        if (url == null) {
            throw new IOException("URL is corrupted for " + path);
        }
        Resource resource = applicationContext.getResource(url.toString());
        InputStream in = resource.getInputStream();
        if (in == null) {
            throw new IOException("Could not open InputStream for resource " + resource.getURI());
        }
        return in;
    }

}
