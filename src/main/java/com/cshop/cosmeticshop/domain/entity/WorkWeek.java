package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * WorkWeek entity
 *
 * @author Pave1Pal
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "work_weeks")
public class WorkWeek extends BaseEntity {

    @OneToMany(fetch = LAZY,
            cascade = {DETACH, MERGE, REFRESH, PERSIST})
    @JoinTable(name = "day_of_week_mapping",
            joinColumns = {@JoinColumn(name = "work_week_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "day_id", referencedColumnName = "id")})
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, WorkDay> daysOfWeek = makeDaysOfWeek();

    @OneToOne(targetEntity = Doctor.class)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private WorkWeekStatus status;

    @Type(type = "uuid-char")
    private UUID activationCode;

    @Transient
    private int numberOfWeek;

    @Transient
    private LocalDate date;


    private Map<DayOfWeek, WorkDay> makeDaysOfWeek() {
        return Stream.of(DayOfWeek.values())
                .collect(Collectors.toMap(dayOfWeek -> dayOfWeek, WorkDay::new));
    }
}
