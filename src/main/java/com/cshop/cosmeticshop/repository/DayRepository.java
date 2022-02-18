package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage Day Entity
 */
@Repository
public interface DayRepository extends JpaRepository<WorkDay, Long> {
}
