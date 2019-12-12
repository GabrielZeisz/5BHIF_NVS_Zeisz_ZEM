package com.nvs.zeisz.nvs.presentation;

import com.nvs.zeisz.nvs.service.dtos.AbstractDto;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public abstract class AbstractController<D extends AbstractDto> {
    abstract ResponseEntity<D> findByIdentifier(@PathVariable String identifier);

    abstract ResponseEntity<List<D>> findAll();

    abstract ResponseEntity<D> create(@RequestBody D dto);

    abstract ResponseEntity<Void> delete(@PathVariable String identifier);

    D addSelfLink(D dto) {
        Link selfLink = linkTo(
                methodOn(this.getClass())
                        .findByIdentifier(dto.getIdentifier())
        ).withSelfRel();

        dto.add(selfLink);
        return dto;
    }
}
