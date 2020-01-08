package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.dtos.PlannerDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
public class Planner extends AbstractModel {

    private String name;
    private String details;
    private LocalDate start; //  = new LocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private LocalDate end; //  = new LocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private String color;

//  @ManyToOne(cascade = CascadeType.PERSIST)
//    private Person person;

    public Planner(PlannerDto plannerDto) {
        super(plannerDto);
        this.name = plannerDto.getName();
        this.details = plannerDto.getDetails();
        this.start = plannerDto.getStart();
        this.end = plannerDto.getEnd();
        this.color = plannerDto.getColor();
    }
}