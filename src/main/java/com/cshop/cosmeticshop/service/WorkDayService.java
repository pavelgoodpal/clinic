package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.time.DayOfWeek;

/**
 * Service to manage doctor WorkDay
 *
 * @author PavelPa1
 */
public interface WorkDayService {

    /**
     * Add treatment period by doctor work day
     *
     * @param period Treatment period you want to add
     * @param workDay WorkDay you want to add treatment period
     * @return true if treatment was added to work day, else return false
     */
    boolean addTreatmentPeriod(TreatmentPeriod period, WorkDay workDay);

    /**
     * Get work day by work week and day of week
     *
     * @param workWeek doctor WorkWeek
     * @param dayOfWeek DayOfWeek enum
     * @return WorkWeek with filling date fields for each day
     */
    WorkDay getWorkDayByWorkWeekAndDayOfWeek(WorkWeek workWeek, DayOfWeek dayOfWeek);
}
