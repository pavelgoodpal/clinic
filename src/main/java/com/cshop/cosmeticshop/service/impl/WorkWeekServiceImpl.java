package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import com.cshop.cosmeticshop.repository.WorkWeekRepository;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkWeekServiceImpl implements WorkWeekService {

    private final WorkWeekRepository workWeekRepository;

    @Override
    public WorkWeek create(WorkWeek workWeek) {
        workWeek.setStatus(WorkWeekStatus.DENIED);
        return workWeekRepository.save(workWeek);
    }
}
