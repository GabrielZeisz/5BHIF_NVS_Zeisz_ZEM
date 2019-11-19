package com.nvs.zeisz.nvs.service;

import com.nvs.zeisz.nvs.model.Jobs;
import com.nvs.zeisz.nvs.model.Person;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto extends AbstractDto {
    private String name;

    private LocalDate bday;

    private Jobs job;

    private String address;

    public PersonDto(Person person) {
        super(person.getIdentifier());
        this.name = person.getName();
        this.bday = person.getBday();
        this.job = person.getJob();
        this.address = person.getAddress();
    }
}
