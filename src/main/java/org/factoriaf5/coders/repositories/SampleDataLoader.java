package org.factoriaf5.coders.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
                new Coder("Judith", "Quiñe Ramos", new GregorianCalendar(1970, Calendar.OCTOBER,7), "Venezuela", "Grado", "Barcelona", "Femtech P2"),
                new Coder("Sandra", "Barrachina A", new GregorianCalendar(1990, Calendar.OCTOBER,7), "Spain", "Grade", "Barcelona", "Femtech P2")
        ));
    }
}