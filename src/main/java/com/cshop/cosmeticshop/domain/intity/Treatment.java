package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Treatment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long price;
    private String description;
    private long treatmentTime;

    public Treatment(){}

}
