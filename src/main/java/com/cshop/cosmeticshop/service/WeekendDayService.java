package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service to manage doctors WeekendDays
 *
 * @author PavelPa1
 */
public interface WeekendDayService {

    /**
     * Create weekend day
     *
     * @param weekendDay Weekend Day
     * @return doctor WeekendDay
     */
    WeekendDay create(WeekendDay weekendDay);

    /**
     * Get all doctor weekend days by doctor
     *
     * @param doctor Doctor
     * @return WeekendDay List
     */
    List<WeekendDay> getDoctorWeekendDays(Doctor doctor);

    /**
     * Get all doctor weekend days by doctor and pageable
     *
     * @param doctor Doctor of weekend day
     * @param pageable Pageable
     * @return WeekendDay page
     */
    Page<WeekendDay> getAllDoctorWeekendDaysBy(Doctor doctor, Pageable pageable);
}
