package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.PersonDto;
import com.nvs.zeisz.nvs.service.UseCasePersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class PersonController extends AbstractController<PersonDto> {

    private final UseCasePersonService useCasePersonService;

    @GetMapping(path = "/persons")
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(useCasePersonService.findAllPersons()
                .stream().map(this::addSelfLink).collect(Collectors.toList()));
    }

    @PostMapping(path = "/persons")
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto person) {
        log.info("Create with: {}", person);
        return ResponseEntity.ok(
                useCasePersonService.savePerson(person)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(path = "/persons/{identifier}")
    public ResponseEntity<PersonDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(useCasePersonService.findPersonByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(path = "/persons/{identifier}")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        useCasePersonService.deletePerson(identifier);
        return ResponseEntity.ok().build();
    }

}
