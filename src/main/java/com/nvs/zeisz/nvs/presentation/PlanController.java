package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.dtos.PlanDto;
import com.nvs.zeisz.nvs.service.PlanService;
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
@RequestMapping(path = "/plans")
public class PlanController extends AbstractController<PlanDto> {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDto>> findAll() {
        return ResponseEntity.ok(planService.findAllPlans()
                .stream().map(this::addSelfLink).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PlanDto> create(@RequestBody PlanDto planDto) {
        log.info("Create with: {}", planDto);
        return ResponseEntity.ok(planService.savePlan(planDto)
                .map(this::addSelfLink)
                .orElseThrow(IllegalArgumentException::new));
    }

    @GetMapping(params = "identifier")
    public ResponseEntity<PlanDto> findByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.of(planService.findPlanByIdentifier(identifier).map(this::addSelfLink));
    }

    @DeleteMapping(params = "identifier")
    public ResponseEntity<Void> delete(@PathVariable String identifier) {
        planService.deletePlan(identifier);
        return ResponseEntity.ok().build();
    }
}
