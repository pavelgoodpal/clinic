package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "work_days")
public class WorkDay extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    @DateTimeFormat
    private LocalDateTime startAt;

    @DateTimeFormat
    private LocalDateTime finishAt;
}
