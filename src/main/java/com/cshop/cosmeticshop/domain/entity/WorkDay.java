package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @ManyToOne(targetEntity = WorkWeek.class)
    @JoinTable(name = "work_week_work_day",
            joinColumns = @JoinColumn(name = "work_day_id"),
            inverseJoinColumns = @JoinColumn(name = "work_week_id"))
    private WorkWeek workWeek;
}
