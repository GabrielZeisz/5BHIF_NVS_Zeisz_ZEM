package com.nvs.zeisz.nvs.persistence;


import com.nvs.zeisz.nvs.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanRepositoryTest {

    @Autowired
    private PlanRepository pr;


    private Planner planner1;
    private  Planner planner2;

    private Person person1;
    private Person person2;
    private Plan plan1;
    private Plan plan2;
    private List<Planner> p1;
    private List<Planner> p2;
    private List<Planner> list1;
    private List<Planner> list2;


    @BeforeEach
    void beforeEach() {

        person1 = Person.builder()
                .name("Helmut Müller")
                .bday(LocalDate.of(1980, 11, 30))
                .job(Jobs.policeman)
                .address("Eisenstadt Josefmüller 28")
                .build();

        person2 = Person.builder()
                .name("Maria Steiner")
                .bday(LocalDate.of(1999, 3, 17))
                .job(Jobs.constructionworker)
                .address("Podersdorf Winklergasse 12")
                .build();

        planner1 = Planner.builder()
                .appointment("Having dinner with family")
                .date(LocalDate.of(2019,4,15))
                .priority(4)
                .time(LocalTime.of(18,30))
                .type(Type.family)
                .plan(plan1)
                .build();

        planner2 = Planner.builder()
                .appointment("Deliver project to boss")
                .date(LocalDate.of(2019,3,23))
                .priority(2)
                .time(LocalTime.of(12,00))
                .type(Type.business)
                .plan(plan2)
                .build();

        p1= new ArrayList<>();
        p2= new ArrayList<>();
        p1.add(planner1);
        p1.add(planner2);
        p2.add(planner2);
        p2.add(planner1);


        list1 = new ArrayList<>();
        list1.add(planner1);
        list1.add(planner2);

        list2 = new ArrayList<>();
        list2.add(planner1);
        list2.add(planner2);

        plan1 = Plan.builder().plan(list1).build();
        plan2 = Plan.builder().plan(list2).build();
    }

    @AfterEach
    void dropIt(){
        pr.deleteAll();
    }

    @Test
    @Transactional
    void findById() {
        pr.save(plan2);
        assertNotNull(pr.findById(plan2.getId()));
    }

    @Test
    @Transactional
    void findAll() {
        List<Plan> plans = new ArrayList<>();

        pr.save(plan1);
        plans.add(plan1);

        pr.save(plan2);
        plans.add(plan2);

        assertEquals(plans, pr.findAll());
    }

    @Test
    void save() {
        pr.save(plan1);
        assertNotNull(pr.findById(plan1.getId()));
    }

    @Test
    void delete() {
        pr.save(plan1);
        assertNotNull(plan1.getId());
        pr.delete(plan1);
        assertTrue(pr.findById(plan1.getId()).isEmpty());
    }
}
