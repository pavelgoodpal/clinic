package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Service for manage WorkWeek.
 *
 * @author Pave1Pal
 */
public interface WorkWeekService {

    /**
     * Create new workWeek in data storage.
     *
     * @param workWeek which will be created
     * @return created workWeek
     */
    WorkWeek create(WorkWeek workWeek);

    /**
     * Update workWeek.
     *
     * @param id       of updated workWeek
     * @param workWeek containing updated info
     * @return updated workWeek
     */
    WorkWeek update(Long id, WorkWeek workWeek);

    /**
     * Get workWeek using id.
     *
     * @param id of workWeek
     * @return found workWeek
     */
    WorkWeek get(Long id);

    /**
     * Transform workWeek to activate status.
     *
     * @param activationCode for activate workWeek
     * @return activated workWeek
     */
    WorkWeek activate(UUID activationCode);

    /**
     * Find WorkWeek by Doctor.
     *
     * @param doctor doctor
     * @return doctor work week
     */
    WorkWeek findByDoctor(Doctor doctor);

    /**
     * Set days of week properties by date.
     *
     * @param workWeek doctor work week
     * @param localDate date
     */
    void setDaysOfWeekDate(WorkWeek workWeek, LocalDate localDate);

    /**
     * Gets doctor work day in chosen date.
     *
     * @param workWeek doctor work week
     * @param date date you want to know doctor schedule
     * @return doctor work day
     */
    WorkDay getWorkDayBy(WorkWeek workWeek, LocalDate date);
}
