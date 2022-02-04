package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class WorkWeekDto {

    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayFinish;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayFinish;

    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private WorkWeekStatus status;

    private UUID activationCode;
}
