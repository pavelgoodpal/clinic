package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekendDayRepository extends JpaRepository<WeekendDay, Long> {

}
