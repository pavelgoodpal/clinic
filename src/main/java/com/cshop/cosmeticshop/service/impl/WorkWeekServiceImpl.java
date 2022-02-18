package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import com.cshop.cosmeticshop.exception.WorkWeekNotFoundException;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.repository.WorkWeekRepository;
import com.cshop.cosmeticshop.service.WorkDayService;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final WorkWeekRepository workWeekRepository;
    private final WorkDayService workDayService;
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
    public WorkWeek findByDoctorId(Long doctorId) {
        return workWeekRepository.findByDoctorId(doctorId)
                .orElseThrow(() -> new WorkWeekNotFoundException("Week not found"));
    }

    @Override
    public WorkWeek getWorkWeekByDoctorIdAndDate(Long doctorId, LocalDate localDate) {
        WorkWeek workWeek = findByDoctorId(doctorId);
        setDaysOfWeekDate(workWeek, localDate);
        return workWeek;
    }

    @Override
    public void setDaysOfWeekDate(WorkWeek workWeek, LocalDate localDate) {
        workWeek.setDate(localDate);
        workWeek.getDaysOfWeek().forEach(this::setDayOfWeekDate);
    }

    private void setDayOfWeekDate(DayOfWeek dayOfWeek, WorkDay workDay) {
        LocalDate dateOfDay = getWorkWeekDateFromWorkDay(workDay);
        LocalDate dayOfWeekDate = findDayOfWeekDate(dayOfWeek ,dateOfDay);
        workDay.setDate(dayOfWeekDate);
    }

    private LocalDate findDayOfWeekDate(DayOfWeek dayOfWeek, LocalDate date) {
        int firstDayOfWeekInDayOfMonth = getFirstDayOfWeekInDayOfMonth(date);
        int dayOfMonth = firstDayOfWeekInDayOfMonth + dayOfWeekNumber(dayOfWeek) - 1;
        int month = date.getMonthValue();
        int year = date.getYear();
        return LocalDate.of(year, month, dayOfMonth);
    }

    private int dayOfWeekNumber(DayOfWeek dayOfWeek) {
        return dayOfWeek.getValue();
    }

    private int getFirstDayOfWeekInDayOfMonth(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        int numberDayOfWeek = date.getDayOfWeek().getValue();
        return dayOfMonth - numberDayOfWeek + 1;
    }

    private LocalDate getWorkWeekDateFromWorkDay(WorkDay workDay) {
        return workDay.getWorkWeek().getDate();
    }

//    @Override
//    @Transactional
//    public boolean addOrderTreatmentPeriodToDayOfWeek(Order order) {
//        TreatmentPeriod treatmentPeriod = order.getTreatmentPeriod();
//        WorkWeek workWeek = getWorkWeekByDoctorIdAndDate(getDoctorIdFrom(order), getDateOfOrder(order));
//        var daysOfWeek = workWeek.getDaysOfWeek();
//        DayOfWeek dayOfWeek = getDayOfWeekFrom(treatmentPeriod);
//        WorkDay workDay = daysOfWeek.get(dayOfWeek);
//        return workDayService.addTreatmentPeriodToWorkDay(treatmentPeriod, workDay);
//    }
//
//    private Long getDoctorIdFrom(Order order) {
//        return order.getDoctor().getId();
//    }
//
//    private DayOfWeek getDayOfWeekFrom(TreatmentPeriod treatmentPeriod) {
//        return treatmentPeriod.getStartAt().getDayOfWeek();
//    }
//
//    private LocalDate getDateOfOrder(Order order) {
//        return LocalDate.from(order.getTreatmentPeriod().getStartAt());
//    }
}
