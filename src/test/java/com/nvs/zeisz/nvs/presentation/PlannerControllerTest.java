package com.nvs.zeisz.nvs.presentation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvs.zeisz.nvs.NvsApplication;
import com.nvs.zeisz.nvs.model.*;
import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
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
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NvsApplication.class)
@AutoConfigureMockMvc
public class PlannerControllerTest {
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

    private Plan plan;

    private Planner planner = Planner.builder()
            .appointment("Having dinner with family")
            .date(LocalDate.of(2019, 4, 15))
            .person(person)
            .priority(4)
            .time(LocalTime.of(18, 30))
            .type(Type.family)
            .plan(plan)
            .build();

    @Test
    void findAllPlanners() throws Exception {
        mockMvc.perform(get("/xplanners"))
                .andExpect(status().isOk());
    }

    @Test
    void createPlanner() throws Exception {
        mockMvc.perform(
                post("/xplanners")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonMapper.writeValueAsString(planner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier").exists());
    }

    @Test
    void findPlannerById() throws Exception {

        MvcResult result = mockMvc.perform(
                post("/xplanners")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonMapper.writeValueAsString(planner))
        ).andExpect(status().isOk())
                .andReturn();

        PlannerDto plannerDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PlannerDto.class);

        mockMvc.perform(
                get("/xplanners/" + plannerDto.getIdentifier()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identifier").value(plannerDto.getIdentifier()));
    }

    @Test
    void deletePlanner() throws Exception {
        MvcResult result = mockMvc.perform(post("/xplanners")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(planner))
        ).andReturn();

        PlannerDto plannerDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PlannerDto.class);

        mockMvc.perform(delete("/xplanners/" + plannerDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());

        mockMvc.perform(get("/xplanners/" + plannerDto.getIdentifier())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isNotFound());
    }
}
