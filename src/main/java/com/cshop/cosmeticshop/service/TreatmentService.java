package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service layer for Treatment entity
 * @author: Pave1Pal
 */
public interface TreatmentService {

    /**
     * Find all treatments using pageable
     * @param pageable Pageable properties
     * @return page of treatments
     */
    Page<Treatment> findAll(Pageable pageable);

    /**
     * Find treatment by id
     * @param id id of treatment
     * @throws TreatmentNotFoundException If treatment are not in data storage
     * @return treatment
     */
    Treatment findById(Long id) throws TreatmentNotFoundException;

    /**
     * Create new treatment in data storage.
     * @param treatment treatment
     * @return saved treatment in data storage
     */
    Treatment create(Treatment treatment);

    /**
     * Update treatment info in data storage.
     * @param treatment treatment
     * @return updated treatment
     */
    Treatment update(Treatment treatment);

    /**
     * Delete treatment by id from data storage.
     * @param id of treatment
     */
    void delete(Long id);
}
