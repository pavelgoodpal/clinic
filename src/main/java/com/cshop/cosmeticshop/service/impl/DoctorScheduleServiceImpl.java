package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.*;
import com.cshop.cosmeticshop.service.DoctorScheduleService;
import com.cshop.cosmeticshop.service.DoctorService;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorService doctorService;
    private final WorkWeekService workWeekService;

    @Override
    public WorkWeek getWorkWeekByDoctorIdAndDate(Long doctorId, LocalDate date) {
        Doctor doctor = doctorService.findById(doctorId);
        WorkWeek doctorWorkWeek = doctor.getWorkWeek();
        List<WeekendDay> doctorWeekendDays = doctor.getWeekendDays();
        workWeekService.setDaysOfWeekDate(doctorWorkWeek, date);
        setWeekendDaysToWorkWeek(doctorWeekendDays, doctorWorkWeek);
        return doctorWorkWeek;
    }

    private void setWeekendDaysToWorkWeek(Collection<WeekendDay> weekendDays, WorkWeek workWeek) {
        var daysOfWeek = workWeek.getDaysOfWeek();
        daysOfWeek.forEach((dayOfWeek, workDay) -> setWorkDayStatus(workDay, weekendDays));
    }

    private void setWorkDayStatus(WorkDay workDay, Collection<WeekendDay> weekendDays) {
        if (workDay.isWorkDay()) {
            boolean isWeekendDay = weekendDays.stream()
                    .anyMatch(weekendDay -> isWeekendDayEqualsWorkDay(weekendDay, workDay));
            workDay.setWorkDay(!isWeekendDay);
        }
    }

    private boolean isWeekendDayEqualsWorkDay(WeekendDay weekendDay, WorkDay workDay) {
        return weekendDay.getDate().equals(workDay.getDate());
    }

    @Override
    public boolean addTreatmentPeriodFromOrderToDayOfWeek(Order order) {
        Long doctorId = order.getDoctor().getId();
        LocalDateTime dateTime = order.getTreatmentPeriod().getStartAt();
        LocalDate date = LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        setWorkWeekByDoctorIdAndCalendar(doctorId, date);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return workWeek.getDaysOfWeek().get(dayOfWeek).addTreatmentPeriod(order.getTreatmentPeriod());
    }

}
