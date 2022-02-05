package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for treatment entity.
 *
 * @author Pave1Pal
 */
@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Long> {

    /**
     * Find all Treatments using Pageable interface.
     *
     * @param pageable Pageable properties
     * @return Page of Treatments - Page<Treatment>
     */
    Page<Treatment> findAll(Pageable pageable);

}
