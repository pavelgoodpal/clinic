package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import lombok.SneakyThrows;

import java.util.UUID;

public interface WorkWeekService {
    WorkWeek create(WorkWeek workWeek);

    @SneakyThrows
    WorkWeek activate(UUID activationCode);
}
