package com.cshop.cosmeticshop.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * Day entity
 *
 * @author PavelPa1
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class WorkDay extends BaseEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @NotNull
    private boolean workDay;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime workStartAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime workFinishAt;

    @OneToMany(fetch = LAZY,
            mappedBy = "workDay",
            cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private List<TreatmentPeriod> treatmentPeriods;

    @Transient
    private LocalDate date;

    @ManyToOne(targetEntity = WorkWeek.class)
    private WorkWeek workWeek;

    public boolean addTreatmentPeriod(TreatmentPeriod period) {
        return treatmentPeriods.add(period);
    }
}
