package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Treatment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long price;
    private String description;
    private long treatmentTime;

    public Treatment(String name, Long price, String description, long treatmentTime) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.treatmentTime = treatmentTime;
    }
}
