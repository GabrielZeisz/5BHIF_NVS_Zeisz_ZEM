package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Plan;
import com.nvs.zeisz.nvs.model.Planner;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDto extends AbstractDto {

    private String name;
    private String details;
    private String start;
    private String end;
    private String color;
    private Plan plan;

    public PlannerDto(Planner planner) {
        super(planner.getIdentifier());
        this.name = planner.getName();
        this.details = planner.getDetails();
        this.start = planner.getStart();
        this.end = planner.getEnd();
        this.color = planner.getColor();
        this.plan = planner.getPlan();
    }

}
