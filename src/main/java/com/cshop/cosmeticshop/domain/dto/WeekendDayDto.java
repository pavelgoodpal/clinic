package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Weekend day", description = "Info about doctor weekend day")
public class WeekendDayDto {

    @Schema(description = "weekend day id")
    private Long id;

    @Schema(description = "name of weekend day")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Schema(description = "weekend day doctor")
    private Doctor doctor;
}
