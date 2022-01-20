package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "work_weeks")
public class WorkWeek extends BaseEntity{

    @OneToOne
    private User doctor;

    @OneToMany(mappedBy = "work_week",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<WorkDay> workDays;

    @Enumerated(EnumType.STRING)
    private WorkWeekStatus status;
}
