package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Plan;
import com.nvs.zeisz.nvs.persistence.PlanRepository;
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
public class UseCasePlanService {

    private final PlanRepository planRepository;

    public List<PlanDto> findAllPlans() {
        return planRepository
                .findAll()
                .stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());
    }


    public Optional<PlanDto> savePlan(PlanDto planDto) {
        return Optional.of(planRepository.save(Optional.of(planDto).map(Plan::new).get()))
                .map(PlanDto::new);
    }


    public Optional<PlanDto> findPlanByIdentifier(String identifier) {
        return planRepository
                .findByIdentifier(identifier)
                .map(PlanDto::new);
    }

    public void deletePlan(String identifier) {
        planRepository.deleteByIdentifier(identifier);
    }
}
