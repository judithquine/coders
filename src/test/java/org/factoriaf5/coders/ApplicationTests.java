package org.factoriaf5.coders;

import org.factoriaf5.coders.repositories.Coder;
import org.factoriaf5.coders.repositories.CoderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Autowired
    CoderRepository coderRepository;

    void returnsTheExitingCoders() throws Exception {

        Coder coder = coderRepository.save(new Coder("Judith", "Quiñe Ramos", "7/10/1970", "Venezuela", "Grado", "Barcelona", "Femtech P2", 51));

        mockMvc.perform(get("/coders"))
                .andExpect(status().isOk())
                .andExpect(view().name("coders/all"))
                .andExpect(model().attribute("coders", hasItem(coder)));
    }

    @BeforeEach
    void setUp() {
        coderRepository.deleteAll();
    }

    @Test
    void returnsAFormToAddNewCoders() throws Exception {
        mockMvc.perform(get("/coders/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("coders/new"));
    }

}
