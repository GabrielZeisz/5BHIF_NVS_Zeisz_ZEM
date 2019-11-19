package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Planner;
import com.nvs.zeisz.nvs.persistence.PlannerRepository;
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
public class UseCasePlannerService {
    private final PlannerRepository plannerRepository;

    public List<PlannerDto> findAllPlanners() {
        return plannerRepository
                .findAll()
                .stream()
                .map(PlannerDto::new)
                .collect(Collectors.toList());
    }


    public Optional<PlannerDto> savePlanner(PlannerDto plannerDto) {
        Planner planner = Optional.of(plannerDto).map(Planner::new).get();
        return Optional.of(plannerRepository.save(planner)).map(PlannerDto::new);
        /*return Optional.of(plannerRepository.save(Optional.of(plannerDto).map(Planner::new).get()))
                .map(PlannerDto::new);*/
    }


    public Optional<PlannerDto> findPlannerByIdentifier(String identifier) {
        return plannerRepository
                .findByIdentifier(identifier)
                .map(PlannerDto::new);
    }

    public void deletePlanner(String identifier) {
        plannerRepository.deleteByIdentifier(identifier);
    }
}
