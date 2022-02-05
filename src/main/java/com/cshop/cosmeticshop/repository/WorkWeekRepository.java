package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for WorkWeek entity.
 *
 * @author Pave1Pal
 */
@Repository
public interface WorkWeekRepository extends CrudRepository<WorkWeek, Long> {

    /**
     * Find WorkWeek using activation code.
     *
     * @param activationCode activation code
     * @return optional workWeek
     */
    Optional<WorkWeek> findByActivationCode(UUID activationCode);
}
