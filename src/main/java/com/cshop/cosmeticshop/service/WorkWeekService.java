package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import lombok.SneakyThrows;

import java.util.UUID;

/**
 * Service for manage WorkWeek.
 *
 * @author Pave1Pal
 */
public interface WorkWeekService {

    /**
     * Create new workWeek in data storage.
     *
     * @param workWeek which will be created
     * @return created workWeek
     */
    WorkWeek create(WorkWeek workWeek);

    /**
     * Update workWeek.
     *
     * @param id of updated workWeek
     * @param workWeek containing updated info
     * @return updated workWeek
     */
    WorkWeek update(Long id, WorkWeek workWeek);

    /**
     * Get workWeek using id.
     *
     * @param id of workWeek
     * @return found workWeek
     */
    WorkWeek get(Long id);

    /**
     * Transform workWeek to activate status.
     *
     * @param activationCode for activate workWeek
     * @return activated workWeek
     */
    @SneakyThrows
    WorkWeek activate(UUID activationCode);
}
