package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.dtos.PersonDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Getter
@Setter
@ToString
public class Person extends AbstractModel {

    private String username;

    private String password;

    private LocalDate bday;

    private String address;

    // , mappedBy = "person"
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Planner> planner;

    public Person(PersonDto personDto) {
        super(personDto);
        this.username = personDto.getUsername();
        this.password = personDto.getPassword();
        this.bday = personDto.getBday();
        this.address = personDto.getAddress();
        this.planner = personDto.getPlanner();
    }
}
