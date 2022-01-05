package org.factoriaf5.coders.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private CoderRepository coderRepository;

    @Autowired
    public SampleDataLoader(CoderRepository coderRepository) {
        this.coderRepository = coderRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        coderRepository.saveAll(List.of(
                new Coder("Judith", "Quiñe Ramos", "07/10/1970", "Venezuela", "Grado", "Barcelona", "Femtech P2", 51),
                new Coder("Sandra", "Barrachina A", "1/01/1990", "Spain", "Grade", "Barcelona", "Femtech P2", 30)
        ));
    }
}