package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.time.LocalDate;

/**
 * Service manage doctor schedule.
 * If you want to get and set doctor work week properties and work day properties use this service
 *
 * @author PavelPa1
 */
public interface DoctorScheduleService {

    /**
     * Get doctor work week by doctor id and date.
     *
     * @param doctorId doctor id
     * @param date date you want to know doctor schedule
     * @return return doctor work week with date information
     */
    WorkWeek getWorkWeekByDoctorIdAndDate(Long doctorId, LocalDate date);

    /**
     * Add treatment period to doctor day of week.
     *
     * @param order order with treatment period
     * @return if treatment period added to day of week returns true, else returns false
     */
    boolean addTreatmentPeriodFromOrderToDayOfWeek(Order order);
}
