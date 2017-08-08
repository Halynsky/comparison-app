package com.improveusa.service;

import com.improveusa.domain.*;
import com.improveusa.domain.porch.PAnswer;
import com.improveusa.domain.porch.PQuestion;
import com.improveusa.domain.porch.PQuestionary;
import com.improveusa.domain.porch.PService;
import com.improveusa.domain.thumbtack.TAnswer;
import com.improveusa.domain.thumbtack.TCategory;
import com.improveusa.domain.thumbtack.TQuestion;
import com.improveusa.domain.thumbtack.TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ImportStorage {

    @Autowired
    private Importer importer;
    private List<TCategory> importedThumbtack;
    private List<PService> importedPorch;

    public List<TCategory> getImportedThumbtack() {
        if (importedThumbtack == null) {
            try {
                importedThumbtack = importer.importThumbtack();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return importedThumbtack;
    }

    public List<PService> getImportedPorch() {
        if (importedPorch == null) {
            try {
                importedPorch = importer.importPorch();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return importedPorch;
    }

    public Statistic statistic(String vendor) {
        int maxQuestions = 0;
        int minQuestions = Integer.MAX_VALUE;
        int coveredServices = 0;
        int allServices = 0;
        int allQuestions = 0;

        if ("porch".equalsIgnoreCase(vendor)) {
            allServices = getImportedPorch().size();
            for (PService service: getImportedPorch()) {
                PQuestionary questionary = service.getQuestionary();
                if (!questionary.isDefault()) {
                    coveredServices++;
                    int count = questionary.getQuestions().size();
                    allQuestions = allQuestions + count;
                    if(count > maxQuestions) {
                        maxQuestions = count;
                    } else if (count < minQuestions){
                        minQuestions = count;
                    }
                }
            }

            return new Statistic().setVendor(vendor)
                    .setCategoriesCount(1)
                    .setServicesCount(allServices)
                    .setMaxQuestions(maxQuestions)
                    .setMinQuestions(minQuestions)
                    .setAverageQuestions(allQuestions/coveredServices)
                    .setQuestionCoverage((coveredServices * 100) / allServices);
        }

        if ("thumbtack".equalsIgnoreCase(vendor)) {
            int catCount = getImportedThumbtack().size();
            for (TCategory category : getImportedThumbtack()) {
                for (TService service : category.getServices()) {
                    allServices++;
                    int count = service.getQuestions().size();
                    allQuestions = allQuestions + count;
                    if (count > 0 ) {
                        coveredServices++;
                        if (count > maxQuestions) {
                            maxQuestions = count;
                        } else if (count < minQuestions) {
                            minQuestions = count;
                        }
                    }
                }
            }

            return new Statistic().setVendor(vendor)
                    .setCategoriesCount(catCount)
                    .setServicesCount(allServices)
                    .setMaxQuestions(maxQuestions)
                    .setMinQuestions(minQuestions)
                    .setAverageQuestions(allQuestions/coveredServices)
                    .setQuestionCoverage((coveredServices * 100)/allServices);
        }
        throw  new IllegalArgumentException(vendor + " is not a valid vendor");
    }

    // ========================== PORCH =======================================
    public List<Category> getPorchCategories(){
        Category all = new Category()
                .setLabel("All Services")
                .setId(1)
                .setServices(convertPServices(getImportedPorch()));
        return Collections.singletonList(all);
    }

    private List<Service> convertPServices(List<PService> pServices) {
        List<Service> services = new ArrayList<>();
        if(pServices != null) {
            services = pServices.stream()
                    .map(p -> new Service()
                            .setLabel(p.getName())
                            .setId(p.getId())
                            .setQuestions(convertPQuestions(p.getQuestionary().getQuestions()))
                    )
                    .sorted((o1, o2) -> o1.getLabel().compareTo(o2.getLabel()) )
                    .collect(Collectors.toList());
        }
        return services;
    }


    private List<Question> convertPQuestions(List<PQuestion> pQuestions) {
        List<Question> questions = new ArrayList<>();
        if (pQuestions != null) {
            questions = pQuestions.stream()
                    .map(p -> new Question()
                            .setLabel(p.getDescription())
                            .setId(p.getId())
                            .setAnswers(convertPAnswers(p.getAnswers()))
                    )
                    .collect(Collectors.toList());
        }
        return questions;
    }

    private List<Answer> convertPAnswers(List<PAnswer> pAnswers) {
        List<Answer> answers = new ArrayList<>();
        if (pAnswers != null) {
            answers = pAnswers.stream()
                    .map(p -> new Answer().setLabel(p.getDescription()).setId(p.getId()))
                    .collect(Collectors.toList());
        }
        return answers;
    }

    // ======================== THUMBTACK ===============================
    public List<Category> Thumbtack(){
        List<Category> all = getImportedThumbtack().stream()
                .map(t -> new Category()
                        .setId(t.getId())
                        .setLabel(t.getName())
                        .setServices(convertTServices(t.getServices())))
                .sorted(Comparator.comparing(Category::getLabel))
                .collect(Collectors.toList());
        return all;
    }

    private List<Service> convertTServices(List<TService> tServices) {
        List<Service> services = new ArrayList<>();
        if(tServices != null) {
            services = tServices.stream()
                    .map(t -> new Service()
                            .setId(t.getId())
                            .setLabel(t.getName())
                            .setQuestions(convertTQuestions(t.getQuestions())))
                    .sorted(Comparator.comparing(Service::getLabel))
                    .collect(Collectors.toList());
        }
        return services;
    }


    private List<Question> convertTQuestions(List<TQuestion> tQuestions) {
        List<Question> questions = new ArrayList<>();
        if (tQuestions != null) {
            questions = tQuestions.stream()
                    .map(t -> new Question()
                            .setLabel(t.getDescription())
                            .setAnswers(convertTAnswers(t.getAnswers())))
                    .collect(Collectors.toList());
        }
        return questions;
    }


    private List<Answer> convertTAnswers(List<TAnswer> tAnswers) {
        List<Answer> answers = new ArrayList<>();
        if (tAnswers != null) {
            answers = tAnswers.stream()
                    .map(tAnswer -> new Answer().setLabel(tAnswer.getDescription()))
                    .collect(Collectors.toList());
        }
        return answers;
    }
}
