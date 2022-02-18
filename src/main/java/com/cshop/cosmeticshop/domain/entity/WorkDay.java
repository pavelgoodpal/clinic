package com.cshop.cosmeticshop.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Day entity
 *
 * @author PavelPa1
 */
@Entity
@Getter
@Setter
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

    @Transient
    private List<TreatmentPeriod> treatmentPeriods;

    @Transient
    private LocalDate date;


    private WorkWeek workWeek;

    public boolean addTreatmentPeriod(TreatmentPeriod period) {
        return treatmentPeriods.add(period);
    }
}
