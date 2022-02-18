package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface WorkWeekRepository extends JpaRepository<WorkWeek, Long> {

    /**
     * Finds WorkWeek using activation code.
     *
     * @param activationCode activation code
     * @return optional workWeek
     */
    Optional<WorkWeek> findByActivationCode(UUID activationCode);

    /**
     * Finds WorkWeek by doctor id.
     *
     * @param doctorId id of Doctor
     * @return optional workWeek
     */
    Optional<WorkWeek> findByDoctorId(Long doctorId);
}
