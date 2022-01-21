package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "doctors")
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {

    @OneToOne
    private WorkWeek workWeek;

    @NotBlank
    private String speciality;
}
