package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Plan;
import com.nvs.zeisz.nvs.model.Planner;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto extends AbstractDto {

    private List<Planner> plan;


    public PlanDto(Plan plan) {
        super(plan.getIdentifier());
        this.plan = plan.getPlan();

    }
}
