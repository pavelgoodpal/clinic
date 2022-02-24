package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository to manage weekend day entity.
 *
 * @author PavelPa1
 */
@Repository
public interface WeekendDayRepository extends JpaRepository<WeekendDay, Long> {

    /**
     * Find weekend days list by doctor.
     *
     * @param doctor weekend day doctor
     * @return weekend days list
     */
    List<WeekendDay> findByDoctor(Doctor doctor);

    /**
     * Find weekend days page by doctor and pageable.
     *
     * @param doctor   weekend days doctor
     * @param pageable pageable
     * @return weekend days page
     */
    Page<WeekendDay> findByDoctor(Doctor doctor, Pageable pageable);
}
