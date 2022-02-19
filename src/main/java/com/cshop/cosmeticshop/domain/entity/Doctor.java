package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("doctor")
public class Doctor extends User {

    @OneToOne(mappedBy = "doctor", fetch = LAZY)
    private WorkWeek workWeek;

    @OneToMany(fetch = LAZY,
            cascade = {MERGE, PERSIST, DETACH, REFRESH})
    private List<WeekendDay> weekendDays;

    @OneToMany(fetch = LAZY,
            cascade = {MERGE, PERSIST, DETACH, REFRESH})
    private List<Order> orders;

    @NotBlank
    private String speciality;
}
