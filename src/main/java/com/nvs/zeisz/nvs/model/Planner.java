package com.nvs.zeisz.nvs.model;


import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
public class Planner extends AbstractModel {

    private LocalDate date;

    private String appointment;

    private int priority;
    @Enumerated(EnumType.STRING)

    private Type type;

    private LocalTime time;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Plan plan;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;

    public Planner(PlannerDto plannerDto) {
        super(plannerDto);
        this.date = plannerDto.getDate();
        this.appointment = plannerDto.getAppointment();
        this.priority = plannerDto.getPriority();
        this.type = plannerDto.getType();
        this.time = plannerDto.getTime();
        this.plan = plannerDto.getPlan();
        this.person = plannerDto.getPerson();
    }

}