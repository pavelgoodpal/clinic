package com.cshop.cosmeticshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Pave1Pal
 *
 * DTO for Treatment class
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentDto {

    private Long id;
    private String name;
    private Long price;
    private String description;
    private long treatmentTime;


}
