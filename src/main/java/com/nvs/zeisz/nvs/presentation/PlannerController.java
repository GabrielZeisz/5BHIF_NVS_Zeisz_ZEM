package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.PlannerDto;
import com.nvs.zeisz.nvs.service.UseCasePlannerService;
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
public class PlannerController extends AbstractController<PlannerDto> {
    private final UseCasePlannerService useCasePlannerService;

    @GetMapping(path = "/xplanners")
    public ResponseEntity<List<PlannerDto>> findAll() {
        return ResponseEntity.ok(useCasePlannerService.findAllPlanners()
                .stream().map(this::addSelfLink).collect(Collectors.toList()));
    }

    @PostMapping(path = "/xplanners")
    public ResponseEntity<PlannerDto> create(@RequestBody PlannerDto planner) {
        log.info("Create with: {}", planner);
        return ResponseEntity.ok(useCasePlannerService.savePlanner(planner)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(path = "/xplanners/{identifier}")
    public ResponseEntity<PlannerDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(useCasePlannerService.findPlannerByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(path = "/xplanners/{identifier}")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        useCasePlannerService.deletePlanner(identifier);
        return ResponseEntity.ok().build();
    }
}
