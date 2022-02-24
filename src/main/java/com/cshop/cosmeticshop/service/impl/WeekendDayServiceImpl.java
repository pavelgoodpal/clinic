package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.repository.WeekendDayRepository;
import com.cshop.cosmeticshop.service.WeekendDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekendDayServiceImpl implements WeekendDayService {

    private final WeekendDayRepository weekendDayRepository;

    @Override
    @Transactional
    public WeekendDay create(WeekendDay weekendDay) {
        return weekendDayRepository.save(weekendDay);
    }

    @Override
    @Transactional
    public List<WeekendDay> getDoctorWeekendDays(Doctor doctor) {
        return weekendDayRepository.findByDoctor(doctor);
    }

    @Override
    @Transactional
    public Page<WeekendDay> getAllDoctorWeekendDaysBy(Doctor doctor, Pageable pageable) {
        return weekendDayRepository.findByDoctor(doctor, pageable);
    }
}
