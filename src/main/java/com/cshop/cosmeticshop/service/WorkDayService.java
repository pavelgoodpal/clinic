package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.time.DayOfWeek;

public interface WorkDayService {

    boolean addTreatmentPeriod(TreatmentPeriod period, WorkDay workDay);

    WorkDay getWorkDayByWorkWeekAndDayOfWeek(WorkWeek workWeek, DayOfWeek dayOfWeek);
}
