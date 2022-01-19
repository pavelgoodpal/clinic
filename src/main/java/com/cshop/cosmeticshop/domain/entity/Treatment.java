package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Treatment entity
 *
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "treatments")
@NoArgsConstructor
public class Treatment extends BaseEntity {

    @Size(min = 2)
    @NotBlank
    private String name;

    @NotNull(message = "fill the price field")
    private Long price;

    @NotBlank
    @Size(min = 5, message = "description must be greater than 5 charsets")
    private String description;

    @NotNull(message = "choose how long treatment will be")
    private Long treatmentTime;
}
