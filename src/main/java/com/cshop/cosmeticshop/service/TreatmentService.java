package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TreatmentService {

    Iterable<Treatment> findAll();

    Page<Treatment> findAll(Pageable pageable);

    Treatment findById(Long id) throws TreatmentNotFoundException;

    Iterable<Treatment> addAll(Iterable<Treatment> entities);

    Treatment update(Treatment treatment);

    void deleteById(Long id);

    void delete(Treatment treatment);
}
