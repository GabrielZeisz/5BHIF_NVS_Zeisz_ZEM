package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UseCasePersonService {
    private final PersonRepository personRepository;

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

    public void deletePerson(String identifier) {
        personRepository.deleteByIdentifier(identifier);
    }
}
