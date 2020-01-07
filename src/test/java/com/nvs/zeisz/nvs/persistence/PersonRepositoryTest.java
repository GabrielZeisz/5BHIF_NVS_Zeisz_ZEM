package com.nvs.zeisz.nvs.persistence;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonRepositoryTest {
//    @Autowired
//    private PersonRepository pr;
//
//    private Person person;
//    private Person person2;
//    private Person person3;
//    private Person person4;
//    private Person person5;
//
//    @BeforeEach
//    void init(){
//        person = Person.builder()
//                .name("Maria Steiner")
//                .bday(LocalDate.of(1999, 3, 17))
//                .job(Jobs.constructionworker)
//                .address("Podersdorf Winklergasse 12")
//                .build();
//
//        person2 = Person.builder()
//                .name("Maria Steiner")
//                .bday(LocalDate.of(1999, 3, 17))
//                .job(Jobs.constructionworker)
//                .address("Podersdorf Winklergasse 12")
//                .build();
//
//        person3 = Person.builder()
//                .name("Marta Hofer")
//                .bday(LocalDate.of(1988, 2, 7))
//                .job(Jobs.policeman)
//                .address("Eisenstadt Haupstraße 2")
//                .build();
//
//        person4 = Person.builder()
//                .name("Rudolf Geier")
//                .bday(LocalDate.of(1977, 1, 3))
//                .job(Jobs.buisnessman)
//                .address("Gols Hintergassenhof 125")
//                .build();
//
//        person5 = Person.builder()
//                .name("Jesse Peer")
//                .bday(LocalDate.of(2001, 8, 18))
//                .job(Jobs.programmer)
//                .address("Illmitz Podersdorferstraße 44")
//                .build();
//
//    }
//
//    @AfterEach
//    void dropIt(){
//        pr.deleteAll();
//    }
//
//
//    @Test
//    @Transactional
//    void findAll(){
//        List<Person> persons = new ArrayList<>();
//
//        pr.save(person4);
//        persons.add(person4);
//
//        pr.save(person5);
//        persons.add(person5);
//
//
//        assertEquals(persons, pr.findAll());
//    }
//
//
//    @Transactional
//    @Test
//    void findById(){
//        pr.save(person2);
//        assertNotNull(pr.findById(person2.getId()));
//    }
//
//    @Test
//    void save(){
//        pr.save(person);
//        assertNotNull(pr.findById(person.getId()));
//    }
//
//    @Test
//    void delete(){
//        pr.save(person3);
//        assertNotNull(person3.getId());
//        pr.delete(person3);
//        assertTrue(pr.findById(person3.getId()).isEmpty());
//    }

}
