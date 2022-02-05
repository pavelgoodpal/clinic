package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.WorkWeekStatus;
import com.cshop.cosmeticshop.exception.WorkWeekNotFoundException;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.repository.WorkWeekRepository;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

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

    @SneakyThrows
    @Override
    public WorkWeek activate(UUID activationCode) {
        WorkWeek workWeek = workWeekRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new WorkWeekNotFoundException(activationCode.toString()));
        workWeek.setStatus(WorkWeekStatus.ACCEPTED);
        workWeek.setActivationCode(null);
        return workWeekRepository.save(workWeek);
    }

    @SneakyThrows
    @Override
    public WorkWeek get(Long id) {
        return workWeekRepository.findById(id)
                .orElseThrow(() -> new WorkWeekNotFoundException(id.toString()));
    }
}
