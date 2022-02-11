package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {

    @OneToOne
    private WorkWeek workWeek;

    @NotBlank
    private String speciality;
}
