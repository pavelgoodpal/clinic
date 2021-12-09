package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Pave1Pal
 * Repository for treatment entity
 */
@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Long> {

    /**
     * method find all Treatments using Pageable interface and return Page of treatments
     */
    Page<Treatment> findAll(Pageable pageable);

}
