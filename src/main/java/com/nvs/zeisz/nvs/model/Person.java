package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.PersonDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
public class Person extends AbstractModel {

    private String name;

    private LocalDate bday;
    @Enumerated(EnumType.STRING)
    private Jobs job;

    private String address;

    public Person(PersonDto personDto) {
        super(personDto);
        this.name = personDto.getName();
        this.bday = personDto.getBday();
        this.job = personDto.getJob();
        this.address = personDto.getAddress();
    }
}
