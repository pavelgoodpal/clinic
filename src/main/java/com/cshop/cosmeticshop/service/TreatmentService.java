package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author:Pave1Pal
 * Service layer for Treatment entity
 */
public interface TreatmentService {

    /**
     * Find all treatments
     */
    Iterable<Treatment> findAll();

    /**
     * Find all treatments using pageable
     */
    Page<Treatment> findAll(Pageable pageable);

    /**
     * Find treatment by id
     */
    Treatment findById(Long id) throws TreatmentNotFoundException;

    /**
     * Add treatments in data storage
     */
    Iterable<Treatment> addAll(Iterable<Treatment> treatments);

    /**
     * Update treatment
     */
    Treatment update(Treatment treatment);

    /**
     * Delete treatment by id
     */
    void deleteById(Long id);

    /**
     * Delete treatment using treatment object
     */
    void delete(Treatment treatment);
}
