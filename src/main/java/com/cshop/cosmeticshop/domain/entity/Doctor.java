package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {

    @OneToOne
    private WorkWeek workWeek;

    @OneToMany
    private List<WeekendDay> weekendDays;

    @OneToMany()
    private List<Order> orders;

    @NotBlank
    private String speciality;
}
