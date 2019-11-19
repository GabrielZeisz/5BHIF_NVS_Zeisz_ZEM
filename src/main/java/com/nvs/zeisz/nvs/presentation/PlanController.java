package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.PlanDto;
import com.nvs.zeisz.nvs.service.UseCasePlanService;
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
public class PlanController extends AbstractController<PlanDto> {

    private final UseCasePlanService useCasePlanService;

    @GetMapping(path = "/plans")
    public ResponseEntity<List<PlanDto>> findAll() {
        return ResponseEntity.ok(useCasePlanService.findAllPlans()
                .stream().map(this::addSelfLink).collect(Collectors.toList()));
    }

    @PostMapping(path = "/plans")
    public ResponseEntity<PlanDto> create(@RequestBody PlanDto planDto) {
        log.info("Create with: {}", planDto);
        return ResponseEntity.ok(useCasePlanService.savePlan(planDto)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(path = "/plans/{identifier}")
    public ResponseEntity<PlanDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(useCasePlanService.findPlanByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(path = "/plans/{identifier}")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        useCasePlanService.deletePlan(identifier);
        return ResponseEntity.ok().build();
    }
}
