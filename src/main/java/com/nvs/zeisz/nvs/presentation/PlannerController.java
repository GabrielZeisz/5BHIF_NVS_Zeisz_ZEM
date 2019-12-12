package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
import com.nvs.zeisz.nvs.service.PlannerService;
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
@RequestMapping(path = "/xplanners")
public class PlannerController extends AbstractController<PlannerDto> {
    private final PlannerService plannerService;

    @GetMapping
    public ResponseEntity<List<PlannerDto>> findAll() {
        return ResponseEntity.ok(plannerService.findAllPlanners()
                .stream().map(this::addSelfLink).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PlannerDto> create(@RequestBody PlannerDto planner) {
        log.info("Create with: {}", planner);
        return ResponseEntity.ok(plannerService.savePlanner(planner)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(params = "/identifier")
    public ResponseEntity<PlannerDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(plannerService.findPlannerByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(params = "identifier")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        plannerService.deletePlanner(identifier);
        return ResponseEntity.ok().build();
    }
}
