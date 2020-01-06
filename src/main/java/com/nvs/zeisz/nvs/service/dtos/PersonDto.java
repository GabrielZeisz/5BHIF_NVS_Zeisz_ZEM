package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Jobs;
import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.model.Plan;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto extends AbstractDto {
    private String name;

    private String password;

    private LocalDate bday;

    private Jobs job;

    private String address;

    private Plan plan;

    public PersonDto(Person person) {
        super(person.getIdentifier());
        this.name = person.getName();
        this.password = person.getPassword();
        this.bday = person.getBday();
        this.job = person.getJob();
        this.address = person.getAddress();
        this.plan = person.getPlan();
    }
}
