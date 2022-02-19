package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;

import java.util.List;

public interface WeekendDayService {

    WeekendDay create(WeekendDay weekendDay);

    List<WeekendDay> getDoctorWeekendDays(Doctor doctor);
}
