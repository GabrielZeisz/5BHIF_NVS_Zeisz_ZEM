package com.nvs.zeisz.nvs.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvs.zeisz.nvs.NvsApplication;
import com.nvs.zeisz.nvs.model.Jobs;
import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.service.dtos.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NvsApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    private Person person = Person.builder()
            .name("Maria Steiner")
            .bday(LocalDate.of(1999, 3, 17))
            .job(Jobs.constructionworker)
            .address("Podersdorf Winklergasse 12")
            .build();

    @Test
    void findAllPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier").exists());
    }

    @Test
    void findPersonById() throws Exception {

        MvcResult result = mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andReturn();

        PersonDto personDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PersonDto.class);

        mockMvc.perform(
                get("/persons/" + personDto.getIdentifier()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier").value(personDto.getIdentifier()));
    }


    @Test
    void deletePerson() throws Exception {
        MvcResult result = mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(person))
        ).andReturn();

        PersonDto personDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PersonDto.class);

        mockMvc.perform(delete("/persons/" + personDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());


        mockMvc.perform(get("/persons/" + personDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }
}
