package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.model.Plan;
import com.nvs.zeisz.nvs.model.Planner;
import com.nvs.zeisz.nvs.model.Type;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlannerDto extends AbstractDto {

    private LocalDate date;
    private String appointment;
    private int priority;
    private Type type;
    private LocalTime time;
    private Plan plan;

    public PlannerDto(Planner planner) {
        super(planner.getIdentifier());
        this.date = planner.getDate();
        this.appointment = planner.getAppointment();
        this.priority = planner.getPriority();
        this.type = planner.getType();
        this.time = planner.getTime();
        this.plan = planner.getPlan();
    }

}
