package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkWeekRepository extends CrudRepository<WorkWeek, Long> {
}
