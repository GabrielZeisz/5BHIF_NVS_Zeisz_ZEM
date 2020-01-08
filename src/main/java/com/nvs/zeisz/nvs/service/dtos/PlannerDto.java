package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.model.Planner;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannerDto extends AbstractDto {

    private String name;
    private String details;
    private LocalDate start;
    private LocalDate end;
    private String color;

    public PlannerDto(Planner planner) {
        super(planner.getIdentifier());
        this.name = planner.getName();
        this.details = planner.getDetails();
        this.start = planner.getStart();
        this.end = planner.getEnd();
        this.color = planner.getColor();
    }
}
