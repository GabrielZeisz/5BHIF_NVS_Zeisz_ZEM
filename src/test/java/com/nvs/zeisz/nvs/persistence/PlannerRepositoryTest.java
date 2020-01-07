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
public class PlannerRepositoryTest {

//    @Autowired
//    private PlannerRepository pr;
//
//    private Planner planner1;
//    private Planner planner2;
//    private Planner planner3;
//    private Planner planner4;
//    private Plan plan;
//
//
//
//    @BeforeEach
//    void beforeEach(){
//
//        Person person1 = Person.builder()
//                .name("Helmut Müller")
//                .bday(LocalDate.of(1980, 11, 30))
//                .job(Jobs.policeman)
//                .address("Eisenstadt Josefmüller 28")
//                .build();
//
//        Person person2 = Person.builder()
//                .name("Maria Steiner")
//                .bday(LocalDate.of(1999, 3, 17))
//                .job(Jobs.constructionworker)
//                .address("Podersdorf Winklergasse 12")
//                .build();
//
//        planner1 = Planner.builder()
//                .appointment("Having dinner with family")
//                .date(LocalDate.of(2019,4,15))
//                .priority(4)
//                .time(LocalTime.of(18,30))
//                .plan(plan)
//                .build();
//
//        planner2 = Planner.builder()
//                .appointment("Deliver project to boss")
//                .date(LocalDate.of(2019,3,23))
//                .priority(2)
//                .time(LocalTime.of(12,00))
//                .plan(plan)
//                .build();
//
//        planner3 = Planner.builder()
//                .appointment("Doing homework")
//                .date(LocalDate.of(2019,4,23))
//                .priority(1)
//                .time(LocalTime.of(18,30))
//                .plan(plan)
//                .build();
//
//        planner4 = Planner.builder()
//                .appointment("Buying new car for son")
//                .date(LocalDate.of(2020,7,12))
//                .priority(3)
//                .time(LocalTime.of(9,10))
//                .plan(plan)
//                .build();
//    }
//
//    @AfterEach
//    void dropIt(){
//        pr.deleteAll();
//    }
//
//    @Test
//    @Transactional
//    void findById(){
//        pr.save(planner2);
//        assertNotNull(pr.findById(planner2.getId()));
//    }
//
//    @Test
//    @Transactional
//    void findAll(){
//        List<Planner> planners = new ArrayList<>();
//
//        pr.save(planner3);
//        planners.add(planner3);
//
//        pr.save(planner4);
//        planners.add(planner4);
//
//
//        assertEquals(planners, pr.findAll());
//    }
//
//    @Test
//    void save(){
//        pr.save(planner1);
//        assertNotNull(pr.findById(planner1.getId()));
//    }
//
//    @Test
//    void delete(){
//        pr.save(planner1);
//        assertNotNull(planner1.getId());
//        pr.delete(planner1);
//        assertTrue(pr.findById(planner1.getId()).isEmpty());
//
//    }

}