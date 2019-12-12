package com.nvs.zeisz.nvs.service.dtos;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Data
public abstract class AbstractDto extends RepresentationModel {
    protected String identifier;
}
