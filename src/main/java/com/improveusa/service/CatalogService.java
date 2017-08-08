package com.improveusa.service;

import com.improveusa.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class CatalogService {

    @Autowired
    private ImportStorage importStorage;

    private List<Category> porchStorage;
    private List<Category> thumbtackStorage;



    public List<Category> getCategories(String vendor) {
        List<Category> categories = new ArrayList<>();
        for (Category cat: getCatalog(vendor)) {
            categories.add(new Category()
                    .setId(cat.getId())
                    .setLabel(cat.getLabel())
                    .setServices(Collections.emptyList()));
        }
        return categories;
    }

    public List<Service> getServices(String vendor, int categoryId) {
        List<Service> services = new ArrayList<>();
        for (Category cat : getCatalog(vendor)) {
            if (cat.getId() == categoryId){
                for (Service ser : cat.getServices()) {
                    services.add(new Service()
                            .setId(ser.getId())
                            .setLabel(ser.getLabel())
                            .setQuestions(Collections.emptyList()));
                }
            }
        }
        return services;
    }

    public List<Question> getQuestions(String vendor, int categoryId, int serviceId) {
        for (Category cat : getCatalog(vendor)) {
            if (cat.getId() == categoryId) {
                for (Service serv : cat.getServices()) {
                    if (serv.getId() == serviceId) {
                        return new ArrayList<>(serv.getQuestions());
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Category> getCatalog(String name){
        if ("porch".equalsIgnoreCase(name)) {
            if (porchStorage == null) {
                porchStorage = importStorage.getPorchCategories();
            }
            return porchStorage;
        }
        if ("thumbtack".equalsIgnoreCase(name)) {
            if (thumbtackStorage == null) {
                thumbtackStorage = importStorage.Thumbtack();
            }
            return thumbtackStorage;
        }
        throw  new IllegalArgumentException(name + " is not a valid vendor");
    }

}
