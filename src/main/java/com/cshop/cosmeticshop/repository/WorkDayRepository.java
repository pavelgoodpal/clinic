package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findByWorkWeekAndDayOfWeek(WorkWeek workWeek, DayOfWeek dayOfWeek);
}
