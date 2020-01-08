package com.nvs.zeisz.nvs.service.dtos;

import com.nvs.zeisz.nvs.model.Person;
import com.nvs.zeisz.nvs.model.Planner;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto extends AbstractDto {
    private String username;

    private String password;

    private LocalDate bday;

    private String address;

    private List<Planner> planner;

    public PersonDto(Person person) {
        super(person.getIdentifier());
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.bday = person.getBday();
        this.address = person.getAddress();
        this.planner = person.getPlanner();
    }
}
