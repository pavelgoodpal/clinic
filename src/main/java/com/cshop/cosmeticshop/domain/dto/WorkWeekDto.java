package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "WorkWeek", description = "Info about doctor work week")
public class WorkWeekDto {

    @Schema(description = "user id")
    private Long id;

    @Schema(description = "monday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayStart;

    @Schema(description = "monday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime mondayFinish;

    @Schema(description = "tuesday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayStart;

    @Schema(description = "tuesday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tuesdayFinish;

    @Schema(description = "wednesday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayStart;

    @Schema(description = "wednesday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime wednesdayFinish;

    @Schema(description = "thursday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayStart;

    @Schema(description = "thursday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime thursdayFinish;

    @Schema(description = "friday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayStart;

    @Schema(description = "friday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fridayFinish;

    @Schema(description = "saturday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayStart;

    @Schema(description = "saturday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime saturdayFinish;

    @Schema(description = "sunday start work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayStart;

    @Schema(description = "sunday finish work day")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime sundayFinish;

    @Schema(description = "work week doctor")
    private Doctor doctor;

    @Schema(description = "status of work week")
    @Enumerated(EnumType.STRING)
    private WorkWeekStatus status;

    @Schema(description = "work week activation code")
    private UUID activationCode;
}
