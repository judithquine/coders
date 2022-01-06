package org.factoriaf5.coders;

import org.factoriaf5.coders.repositories.Coder;
import org.factoriaf5.coders.repositories.CoderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(view().name("coders/edit"))
                .andExpect(model().attributeExists("coder"))
                .andExpect(model().attribute("title", "Create new coder"));
    }

    @Test
    void allowsToCreateANewCoder() throws Exception {
        mockMvc.perform(post("/coders/new")
                        .param("name", "Ana")
                        .param("surnames", "Casas D")
                        .param("birthday", "01/01/1990")
                        .param("country", "Venezuela")
                        .param("studies", "grade")
                        .param("address", "Barcelona")
                        .param("promotion", "Femtech P2")
                        .param("age", "30")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coders"))
        ;

        List<Coder> existingCoders = (List<Coder>) coderRepository.findAll();
        assertThat(existingCoders, contains(allOf(
                hasProperty("name", equalTo("Ana")),
                hasProperty("surnames", equalTo("Casas D")),
                hasProperty("birthday", equalTo("01/01/1990")),
                hasProperty("country", equalTo("Venezuela")),
                hasProperty("studies", equalTo("grade")),
                hasProperty("address", equalTo("Barcelona")),
                hasProperty("promotion", equalTo("Femtech P2")),
                hasProperty("age", equalTo(30))
        )));
    }

    @Test
    void returnsAFormToEditCoders() throws Exception {
        Coder coder = coderRepository.save(new Coder("Sara", "J","01/01/1990", "Spain", "Grade", "Barcelona", "FemTech P2", 40));
        mockMvc.perform(get("/coders/edit/" + coder.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("coders/edit"))
                .andExpect(model().attribute("coder", coder))
                .andExpect(model().attribute("title", "Edit coder"));
    }

}
