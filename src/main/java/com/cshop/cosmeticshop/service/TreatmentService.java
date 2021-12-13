package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author:Pave1Pal
 * Service layer for Treatment entity
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
}
