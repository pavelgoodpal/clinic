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

    @Schema(description = "work week id")
    private Long id;

    @Schema(description = "days of week map: name of week in caps is key, value is work days object",
            required = true)
    private Map<DayOfWeek, WorkDay> daysOfWeek = makeDaysOfWeek();

    @Schema(description = "doctor object, just use id", required = true)
    private Doctor doctor;

    @Schema(description = "work week status. ACCEPTED, DENIED")
    private WorkWeekStatus status;

    @Schema(description = "activation code in uuid", required = false)
    private UUID activationCode;

    @Schema(description = "number of week in year")
    private int numberOfWeek;

    @Schema(description = "date of week", type = "LocalDate")
    private LocalDate date;

    private Map<DayOfWeek, WorkDay> makeDaysOfWeek() {
        return Stream.of(DayOfWeek.values())
                .collect(Collectors.toMap(dayOfWeek -> dayOfWeek, WorkDay::new));
    }
}
