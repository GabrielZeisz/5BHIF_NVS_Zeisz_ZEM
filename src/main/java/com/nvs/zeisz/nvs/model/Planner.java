package com.nvs.zeisz.nvs.model;


import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
public class Planner extends AbstractModel {

    private String name;
    private String details;
    private String start; //  = new LocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private String end; //  = new LocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private String color;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Plan plan;

    public Planner(PlannerDto plannerDto) {
        super(plannerDto);
        this.name = plannerDto.getName();
        this.details = plannerDto.getDetails();
        this.start = plannerDto.getStart();
        this.end = plannerDto.getEnd();
        this.color = plannerDto.getColor();
        this.plan = plannerDto.getPlan();
    }

}