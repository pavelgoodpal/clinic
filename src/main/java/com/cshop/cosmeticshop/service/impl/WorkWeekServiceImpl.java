package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import com.cshop.cosmeticshop.exception.WorkWeekNotFoundException;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.repository.WorkWeekRepository;
import com.cshop.cosmeticshop.service.WorkDayService;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Implements WorkWeekService interface.
 *
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class WorkWeekServiceImpl implements WorkWeekService {

    private final WorkDayService workDayService;
    private final WorkWeekRepository workWeekRepository;
    private final WorkWeekMapper workWeekMapper;

    @Override
    public WorkWeek create(WorkWeek workWeek) {
        workWeek.setStatus(WorkWeekStatus.DENIED);
        workWeek.setActivationCode(UUID.randomUUID());
        return workWeekRepository.save(workWeek);
    }

    @Override
    public WorkWeek update(Long id, WorkWeek workWeek) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> workWeekMapper.merge(current, workWeek))
                .map(target -> {
                    target.setStatus(WorkWeekStatus.DENIED);
                    target.setActivationCode(UUID.randomUUID());
                    return target;
                })
                .map(workWeekRepository::save)
                .orElseThrow();
    }

    @Override
    public WorkWeek activate(UUID activationCode) {
        WorkWeek workWeek = workWeekRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new WorkWeekNotFoundException(activationCode.toString()));
        workWeek.setStatus(WorkWeekStatus.ACCEPTED);
        workWeek.setActivationCode(null);
        return workWeekRepository.save(workWeek);
    }

    @Override
    public WorkWeek get(Long id) {
        return workWeekRepository.findById(id)
                .orElseThrow(() -> new WorkWeekNotFoundException(id.toString()));
    }

    @Override
    @Transactional
    public WorkWeek findByDoctor(Doctor doctor) {
        WorkWeek workWeek = workWeekRepository.findByDoctor(doctor)
                .orElseThrow(() -> new WorkWeekNotFoundException("Week not found"));
        return workWeek;
    }

    @Override
    public void setDaysOfWeekDate(WorkWeek workWeek, LocalDate date) {
        workWeek.setDate(date);
        workWeek.getDaysOfWeek().forEach((dayOfWeek, workDay) -> this.setDayOfWeekDate(dayOfWeek, workDay, date));
    }

    /**
     * Set date to doctor work day of week.
     *
     * @param dayOfWeek day of week enum
     * @param workDay   doctor work day
     */
    private void setDayOfWeekDate(DayOfWeek dayOfWeek, WorkDay workDay, LocalDate date) {
        LocalDate dayOfWeekDate = findDayOfWeekDate(dayOfWeek, date);
        workDay.setDate(dayOfWeekDate);
    }

    /**
     * Find day of week date by date of one week day.
     *
     * @param dayOfWeek day of week enum
     * @param date      date
     * @return day of week date
     */
    private LocalDate findDayOfWeekDate(DayOfWeek dayOfWeek, LocalDate date) {
        int firstDayOfWeekInDayOfMonth = getFirstDayOfWeekInDayOfMonth(date);
        int dayOfMonth = firstDayOfWeekInDayOfMonth + dayOfWeekNumber(dayOfWeek) - 1;
        int month = date.getMonthValue();
        int year = date.getYear();
        return LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Number day of week. Started with 1.
     *
     * @param dayOfWeek day of week
     * @return number day of week
     */
    private int dayOfWeekNumber(DayOfWeek dayOfWeek) {
        return dayOfWeek.getValue();
    }

    /**
     * Get first day of week in day of month by date another day.
     *
     * @param date another day date
     * @return day of month number
     */
    private int getFirstDayOfWeekInDayOfMonth(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        int numberDayOfWeek = date.getDayOfWeek().getValue();
        return dayOfMonth - numberDayOfWeek + 1;
    }


    @Override
    public WorkDay getWorkDayBy(WorkWeek workWeek, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return workDayService.getWorkDayByWorkWeekAndDayOfWeek(workWeek, dayOfWeek);
    }
}
