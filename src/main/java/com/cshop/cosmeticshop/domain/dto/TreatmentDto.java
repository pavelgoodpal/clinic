package com.cshop.cosmeticshop.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO class for Treatment
 * @author Pave1Pal
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentDto {

    private Long id;

    @Size(min=2, message = "name must be greater than 2")
    @NotBlank
    private String name;

    @NotNull(message = "fill the price field")
    private Long price;

    @NotBlank
    @Size(min=5, message="description must be greater than 5 charsets")
    private String description;

    @NotNull(message = "choose how long treatment will be")
    private long treatmentTime;


}
