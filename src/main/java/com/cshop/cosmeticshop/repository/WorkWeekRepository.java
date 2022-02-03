package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkWeekRepository extends CrudRepository<WorkWeek, Long> {

    Optional<WorkWeek> findByActivationCode(UUID activationCode);
}
