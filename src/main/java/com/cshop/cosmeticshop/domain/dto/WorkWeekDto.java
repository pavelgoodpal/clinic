package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Setter
@Schema(name = "WorkWeek", description = "Info about doctor work week")
public class WorkWeekDto {

    private Long id;

    private Map<DayOfWeek, WorkDay> daysOfWeek = makeDaysOfWeek();

    private Doctor doctor;

    private WorkWeekStatus status;

    private UUID activationCode;

    private int numberOfWeek;

    private LocalDate date;

    private Map<DayOfWeek, WorkDay> makeDaysOfWeek() {
        return Stream.of(DayOfWeek.values())
                .collect(Collectors.toMap(dayOfWeek -> dayOfWeek, WorkDay::new));
    }
}
