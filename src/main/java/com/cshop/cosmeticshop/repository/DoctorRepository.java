package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for doctor entity.
 *
 * @author Pave1Pal
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Find all doctors using pageable interface.
     *
     * @param pageable page parameters
     * @return page of doctor
     */
    Page<Doctor> findAll(Pageable pageable);
}
