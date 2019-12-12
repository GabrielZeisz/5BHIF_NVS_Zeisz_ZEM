package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.dtos.PersonDto;
import com.nvs.zeisz.nvs.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@RequestMapping(path = "/persons")
public class PersonController extends AbstractController<PersonDto> {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(personService.findAllPersons()
                .stream().map(this::addSelfLink)
                .collect(Collectors.toList()));
    }

    @GetMapping(params = "name")
    public ResponseEntity<PersonDto> findByName(@RequestParam String name) {
        return ResponseEntity.of(personService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto person) {
        log.info("Create with: {}", person);
        return ResponseEntity.ok(
                personService.savePerson(person)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(params = "identifier")
    public ResponseEntity<PersonDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(personService.findPersonByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(params = "identifier")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        personService.deletePerson(identifier);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/login")
    public ResponseEntity<PersonDto> authenticateUser(@RequestBody PersonDto personDto){
        boolean authenticated = personService.loginAccount(personDto);
        HttpStatus httpStatus = authenticated ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        if(httpStatus == HttpStatus.OK)

            return ResponseEntity.ok(personService.findByName(personDto.getName()).get());
        else
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping(path="/register")
    public ResponseEntity<Optional<PersonDto>> registerUser(@RequestBody PersonDto personDto){
        try {
            return ResponseEntity.ok(personService.createUser(personDto));
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
