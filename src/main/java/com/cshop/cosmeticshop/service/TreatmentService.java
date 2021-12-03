package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface TreatmentService {

    Iterable<Treatment> findAll();

    Treatment findById(Long id) throws TreatmentNotFoundException;
}
