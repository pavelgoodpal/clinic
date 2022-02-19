package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.*;
import com.cshop.cosmeticshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorService doctorService;
    private final WeekendDayService weekendDayService;
    private final WorkWeekService workWeekService;
    private final WorkDayService workDayService;

    @Override
    public WorkWeek getWorkWeekByDoctorIdAndDate(Long doctorId, LocalDate date) {
        Doctor doctor = doctorService.findById(doctorId);
        WorkWeek doctorWorkWeek = workWeekService.findByDoctor(doctor);
        List<WeekendDay> doctorWeekendDays = weekendDayService.getDoctorWeekendDays(doctor);
        workWeekService.setDaysOfWeekDate(doctorWorkWeek, date);
        setWeekendDaysToWorkWeek(doctorWeekendDays, doctorWorkWeek);
        return doctorWorkWeek;
    }

    /**
     * Set weekend days to work week.
     *
     * @param weekendDays doctor weekend days
     * @param workWeek    doctor work week
     */
    private void setWeekendDaysToWorkWeek(Collection<WeekendDay> weekendDays, WorkWeek workWeek) {
        Map<DayOfWeek, WorkDay> daysOfWeek = workWeek.getDaysOfWeek();
        daysOfWeek.forEach((dayOfWeek, workDay) -> setWorkDayStatus(workDay, weekendDays));
    }

    /**
     * Set work week status by doctor work day and doctor weekend days.
     *
     * @param workDay     doctor work day
     * @param weekendDays doctor weekend days
     */
    private void setWorkDayStatus(WorkDay workDay, Collection<WeekendDay> weekendDays) {
        if (workDay.isWorkDay()) {
            boolean isWeekendDay = weekendDays.stream()
                    .anyMatch(weekendDay -> isWeekendDayEqualsWorkDay(weekendDay, workDay));
            workDay.setWorkDay(!isWeekendDay);
        }
    }

    /**
     * Check is doctor weekend day equals doctor work day.
     *
     * @param weekendDay doctor weekend day
     * @param workDay    doctor work day
     * @return true if weekend day equals work day, else return false.
     */
    private boolean isWeekendDayEqualsWorkDay(WeekendDay weekendDay, WorkDay workDay) {
        return weekendDay.getDate().equals(workDay.getDate());
    }

    @Override
    public boolean addTreatmentPeriodFromOrderToDayOfWeek(Order order) {
        Long doctorId = order.getDoctor().getId();
        LocalDateTime orderStartAt = order.getStartAt();
        TreatmentPeriod treatmentPeriod = getTreatmentPeriodFrom(order);
        LocalDate dateOfOrder = orderStartAt.toLocalDate();
        WorkWeek doctorWorkWeek = getWorkWeekByDoctorIdAndDate(doctorId, dateOfOrder);
        WorkDay doctorWorkDay = workWeekService.getWorkDayBy(doctorWorkWeek, dateOfOrder);
        return workDayService.addTreatmentPeriod(treatmentPeriod, doctorWorkDay);
    }

    /**
     * Make treatment period from order
     *
     * @param order order
     * @return treatment period
     */
    private TreatmentPeriod getTreatmentPeriodFrom(Order order) {
        TreatmentPeriod treatmentPeriod = new TreatmentPeriod();
        treatmentPeriod.setStartAt(order.getStartAt());
        treatmentPeriod.setFinishAt(order.getFinishAt());
        return treatmentPeriod;
    }

}
