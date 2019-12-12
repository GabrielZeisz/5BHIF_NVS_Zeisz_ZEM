package com.nvs.zeisz.nvs.model;

import com.nvs.zeisz.nvs.service.dtos.AbstractDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@Getter
public class AbstractModel extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotNull
    protected String identifier = UUID.randomUUID().toString();

    AbstractModel(AbstractDto abstractDto) {
        this.identifier =
                Optional.ofNullable(abstractDto.getIdentifier())
                        .orElse(UUID.randomUUID().toString());
    }
}
