package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.repository.WeekendDayRepository;
import com.cshop.cosmeticshop.service.WeekendDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekendDayServiceImpl implements WeekendDayService {

    private final WeekendDayRepository weekendDayRepository;

    @Override
    public WeekendDay create(WeekendDay weekendDay) {
        return weekendDayRepository.save(weekendDay);
    }
}
