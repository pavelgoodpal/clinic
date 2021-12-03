package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pave1Pal
 *
 * DTO for Treatment class
 */

@Data
@AllArgsConstructor
public class TreatmentDTO {

    private String name;
    private Long price;
    private String description;
    private long treatmentTime;

    /**
     * Return map treatment object
     */
    public Treatment toTreatment() {
        return new Treatment(name, price, description, treatmentTime);
    }

}
