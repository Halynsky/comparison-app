package com.improveusa.controller;

import com.improveusa.domain.Category;
import com.improveusa.domain.Question;
import com.improveusa.domain.Service;
import com.improveusa.service.CatalogService;
import com.improveusa.domain.Statistic;
import com.improveusa.service.ImportStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.improveusa.util.Constants.*;


@RestController
@RequestMapping(CATALOG_PATH)
public class CatalogResource {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ImportStorage importStorage;


    @GetMapping(ALL_SUB_PATH)
    public ResponseEntity<List<Category>> getCatalog(@PathVariable("vendor") String vendor) {
        List<Category> categories = catalogService.getCatalog(vendor);
        return ResponseEntity.ok()
                .body(categories);
    }

    @GetMapping(STATISTIC_SUB_PATH)
    public ResponseEntity<Statistic> getStatistic(@PathVariable("vendor") String vendor) {
        Statistic statistic = importStorage.statistic(vendor);
        return ResponseEntity.ok()
                .body(statistic);
    }

    @GetMapping(CATEGORIES_SUB_PATH)
    public  ResponseEntity<List<Category>> getCategories(@PathVariable("vendor") String vendor) {
        List<Category> categories = catalogService.getCategories(vendor);
        return ResponseEntity.ok()
                .body(categories);
    }

    @GetMapping(SERVICES_SUB_PATH)
    public  ResponseEntity<List<Service>> getServices(@PathVariable("vendor") String vendor,
                                                      @PathVariable("cid") int categoryId) {
        List<Service> services = catalogService.getServices(vendor, categoryId);
        return ResponseEntity.ok()
                .body(services);
    }

    @GetMapping(QUESTIONS_SUB_PATH)
    public ResponseEntity<List<Question>> getQuestions(@PathVariable("vendor") String vendor,
                                                       @PathVariable("cid") int categoryId,
                                                       @PathVariable("sid") int serviceId) {
        List<Question> questions = catalogService.getQuestions(vendor, categoryId, serviceId);
        return ResponseEntity.ok()
                .body(questions);
    }
}
