package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.dtos.PersonDto;
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
@Setter
@ToString
public class Person extends AbstractModel {

    private String name;

    private String password;

    private LocalDate bday;
    @Enumerated(EnumType.STRING)
    private Jobs job;


    private String address;

    public Person(PersonDto personDto) {
        super(personDto);
        this.name = personDto.getName();
        this.password = personDto.getPassword();
        this.bday = personDto.getBday();
        this.job = personDto.getJob();
        this.address = personDto.getAddress();
    }
}
