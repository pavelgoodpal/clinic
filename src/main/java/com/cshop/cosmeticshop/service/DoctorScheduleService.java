package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.time.LocalDate;

public interface DoctorScheduleService {

    WorkWeek getWorkWeekByDoctorIdAndDate(Long doctorId, LocalDate date);

    boolean addTreatmentPeriodFromOrderToDayOfWeek(Order order);
}
