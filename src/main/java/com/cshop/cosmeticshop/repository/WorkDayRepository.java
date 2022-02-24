package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

/**
 * To manage WorkDay entity
 *
 * @author PavelPa1
 */
@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    /**
     * Find optional work day by work week and day of week
     *
     * @param workWeek  work week info
     * @param dayOfWeek day of week enum
     * @return optional work day
     */
    Optional<WorkDay> findByWorkWeekAndDayOfWeek(WorkWeek workWeek, DayOfWeek dayOfWeek);
}
