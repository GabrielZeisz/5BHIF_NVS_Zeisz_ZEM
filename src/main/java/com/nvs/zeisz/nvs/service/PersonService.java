package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.persistence.PersonRepository;
import com.nvs.zeisz.nvs.service.dtos.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PersonService {
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

    public Optional<PersonDto> findByName(String name)
    {
        return personRepository.findByName(name).map(this::mapDTO);
    }

    public void deletePerson(String identifier) {
        personRepository.deleteByIdentifier(identifier);
    }

    public Optional<PersonDto> createUser(PersonDto personDto){
        if(personRepository.findByName(personDto.getName()).isPresent()){
            throw new UsernameNotFoundException("Username is already taken!");
        }
        Person person = Optional.of(personDto).map(Person::new).get();
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return Optional.of(personRepository.save(person)).map(PersonDto::new);
    }

    public boolean loginAccount(PersonDto personDto){
        Person loginUser =  Optional.of(personDto).map(Person::new).get();
        Person checkUser = personRepository.findByName(loginUser.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Username or password!"));
        boolean test =  passwordEncoder.
                matches(
                        loginUser.getPassword(),
                        checkUser.getPassword());
        return test;
    }
}