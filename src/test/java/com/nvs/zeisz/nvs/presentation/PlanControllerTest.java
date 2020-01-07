package com.nvs.zeisz.nvs.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvs.zeisz.nvs.NvsApplication;
import com.nvs.zeisz.nvs.model.*;
import com.nvs.zeisz.nvs.service.dtos.PlanDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NvsApplication.class)
@AutoConfigureMockMvc
public class PlanControllerTest {
//    @Autowired
//    private ObjectMapper jsonMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Person person = Person.builder()
//            .name("Maria Steiner")
//            .bday(LocalDate.of(1999, 3, 17))
//            .job(Jobs.constructionworker)
//            .address("Podersdorf Winklergasse 12")
//            .build();
//
//    private Plan plan;
//
//    private List<Planner> list = new ArrayList<>();
//
//
//    private Planner planner = Planner.builder()
//            .appointment("Having dinner with family")
//            .date(LocalDate.of(2019, 4, 15))
//            .priority(4)
//            .time(LocalTime.of(18, 30))
//            .type(Type.family)
//            .plan(plan)
//            .build();
//
//    @BeforeEach
//    void init() {
//        list.add(planner);
//        plan = Plan.builder().plan(list).build();
//    }
//
//    @AfterEach
//    void clearing() {
//        list.clear();
//    }
//
//    @Test
//    void findAllPlans() throws Exception {
//        mockMvc.perform(get("/plans"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void createPlan() throws Exception {
//
//
//        mockMvc.perform(
//                post("/plans")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(jsonMapper.writeValueAsString(plan)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.identifier").exists());
//
//        list.clear();
//    }
//
//    @Test
//    void findPlanById() throws Exception {
//
//        list.add(planner);
//
//        MvcResult result = mockMvc.perform(
//                post("/plans")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(jsonMapper.writeValueAsString(plan))
//        ).andExpect(status().isOk())
//                .andReturn();
//
//        PlanDto planDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PlanDto.class);
//
//        mockMvc.perform(
//                get("/plans/" + planDto.getIdentifier()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.identifier").value(planDto.getIdentifier()));
//
//        list.clear();
//    }
//
//    @Test
//    void deletePlan() throws Exception {
//        MvcResult result = mockMvc.perform(post("/plans")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(jsonMapper.writeValueAsString(plan))
//        ).andReturn();
//
//        PlanDto planDto = jsonMapper.readValue(result.getResponse().getContentAsString(), PlanDto.class);
//
//        mockMvc.perform(delete("/plans/" + planDto.getIdentifier())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//        ).andExpect(status().isOk());
//
//        mockMvc.perform(get("/plans/" + planDto.getIdentifier())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//        ).andExpect(status().isNotFound());
//    }
}
