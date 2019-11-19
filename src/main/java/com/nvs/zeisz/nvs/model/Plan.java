package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.PlanDto;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
public class Plan extends AbstractModel {

    @OneToMany(mappedBy = "plan", cascade = CascadeType.PERSIST)
    private List<Planner> plan = new ArrayList<>();

    public Plan(PlanDto planDto) {
        super(planDto);
        this.plan = planDto.getPlan();
    }


}
