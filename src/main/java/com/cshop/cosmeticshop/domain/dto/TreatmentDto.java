package com.cshop.cosmeticshop.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO class for Treatment
 *
 * @author Pave1Pal
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Treatment", description = "Info about treatment")
public class TreatmentDto {

    @Schema(description = "Treatment id")
    private Long id;

    @Schema(description = "Name of treatment",
            required = true,
            minLength = 2)
    @Size(min = 2, message = "name must be greater than 2")
    @NotBlank
    private String name;

    @Schema(description = "Price of treatment",
            required = true)
    @NotNull(message = "fill the price field")
    private Long price;

    @Schema(description = "Description of treatment",
            required = true,
            minLength = 5)
    @NotBlank
    @Size(min = 5, message = "description must be greater than 5 charsets")
    private String description;

    @Schema(description = "Time which treatment can take",
            required = true)
    @NotNull(message = "choose how long treatment will be")
    private long treatmentTime;


}
