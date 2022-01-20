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
@DiscriminatorValue("2")
public class Doctor extends User {

    @OneToOne
    @JoinTable(name = "doctor_work_week",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "work_week_id"))
    private WorkWeek workWeek;

    @NotBlank
    private String speciality;
}
