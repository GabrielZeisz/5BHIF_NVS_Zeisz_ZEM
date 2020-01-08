package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.model.Planner;
import com.nvs.zeisz.nvs.persistence.PersonRepository;
import com.nvs.zeisz.nvs.service.dtos.PersonDto;
import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PersonService {

    private final PlannerService plannerService;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<PersonDto> findAllPersons() {
        return personRepository
                .findAll()
                .stream()
                .map(PersonDto::new)
                .collect(Collectors.toList());
    }


    public Optional<PersonDto> savePerson(PersonDto personDto) {
        return Optional.of(personRepository.save(Optional.of(personDto).map(Person::new).get()))
                .map(PersonDto::new);
    }


    public Optional<PersonDto> findPersonByIdentifier(String identifier) {
        return personRepository
                .findByIdentifier(identifier)
                .map(PersonDto::new);
    }

    PersonDto mapDTO(Person model) {
        return new PersonDto(model);
    }

    public Optional<PersonDto> findByName(String name) {
        return personRepository.findByName(name).map(this::mapDTO);
    }

    public void deletePerson(String identifier) {
        personRepository.deleteByIdentifier(identifier);
    }

    public Optional<PersonDto> createUser(PersonDto personDto) {
        if (personRepository.findByName(personDto.getName()).isPresent()) {
            throw new UsernameNotFoundException("Username is already taken!");
        }
//        Person person = Optional.of(personDto).map(Person::new).get();
        personDto.setPassword(passwordEncoder.encode(personDto.getPassword()));
        Person person = Optional.of(personDto).map(Person::new).get();
        return Optional.of(personRepository.save(person)).map(PersonDto::new);
    }

    public PersonDto loginAccount(PersonDto personDto) throws AuthenticationException {
//        Person loginUser =  Optional.of(personDto).map(Person::new).get();
        Person checkUser = personRepository.findByName(personDto.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Username or password!"));
        if (passwordEncoder.matches(personDto.getPassword(), checkUser.getPassword()))
            return Optional.of(checkUser).map(PersonDto::new).get();
        throw new AuthenticationException("Username and password don't match");
    }

    private List<Planner> kurtplanner = new ArrayList<>();
    private List<Planner> sepplanner = new ArrayList<>();
    private List<Planner> jochenplanner = new ArrayList<>();

    @PostConstruct
    public void init() {
        Planner pl1 = Planner.builder().name("Buisness Meeting").details("Jour Fixe with my boss and fixing appointments").start(LocalDate.of(2020, 1, 8)).end(LocalDate.of(2020, 1, 8)).color("blue").build();
        Planner pl2 = Planner.builder().name("Family Dinner").details("Go to my family and drive with them to the diner").start(LocalDate.of(2020, 1, 9)).end(LocalDate.of(2020, 1, 9)).color("red").build();
        Planner pl3 = Planner.builder().name("Vaccation").details("Driving to Costa Rica for vaccation").start(LocalDate.of(2020, 1, 10)).end(LocalDate.of(2020, 1, 18)).color("green").build();
        Planner pl4 = Planner.builder().name("Buisness Meeting").details("Jour Fixe with my boss and fixing appointments").start(LocalDate.of(2020, 1, 9)).end(LocalDate.of(2020, 1, 10)).color("purple").build();
        Planner pl5 = Planner.builder().name("continuing education course").details("Drive to Vegas for continuing education course").start(LocalDate.of(2020, 1, 2)).end(LocalDate.of(2020, 1, 18)).color("blue").build();
        Planner pl6 = Planner.builder().name("Don`t eat sweets").details("Doctor said I should not eat sweets for a while").start(LocalDate.of(2020, 1, 12)).end(LocalDate.of(2020, 1, 20)).color("blue").build();

        kurtplanner.add(pl1);
        kurtplanner.add(pl2);
        sepplanner.add(pl3);
        sepplanner.add(pl4);
        jochenplanner.add(pl5);
        jochenplanner.add(pl6);

        Person p1 = Person.builder().name("kurt").password("kurt").address("Kurtstraße 1").bday(LocalDate.of(1980, 1, 1)).planner(kurtplanner).build();
        Person p2 = Person.builder().name("sepp").password("sepp").address("Seppstraße 1").bday(LocalDate.of(1980, 1, 1)).planner(sepplanner).build();
        Person p3 = Person.builder().name("jochen").password("jochen").address("Jochenstraße 1").bday(LocalDate.of(1980, 1, 1)).planner(jochenplanner).build();

        plannerService.savePlanner(Optional.of(pl1).map(PlannerDto::new).get());
        plannerService.savePlanner(Optional.of(pl2).map(PlannerDto::new).get());
        plannerService.savePlanner(Optional.of(pl3).map(PlannerDto::new).get());
        plannerService.savePlanner(Optional.of(pl4).map(PlannerDto::new).get());
        plannerService.savePlanner(Optional.of(pl5).map(PlannerDto::new).get());
        plannerService.savePlanner(Optional.of(pl6).map(PlannerDto::new).get());

        createUser(Optional.of(p1).map(PersonDto::new).get());
        createUser(Optional.of(p2).map(PersonDto::new).get());
        createUser(Optional.of(p3).map(PersonDto::new).get());
    }
}
