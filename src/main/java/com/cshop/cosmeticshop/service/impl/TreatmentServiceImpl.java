package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.repository.TreatmentRepository;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class implements TreatmentService.
 *
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Override
    @Transactional
    public Page<Treatment> findAll(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Treatment findById(Long id) throws TreatmentNotFoundException {
        return treatmentRepository.findById(id).orElseThrow(() ->
                new TreatmentNotFoundException("Treatment not Found"));
    }

    @Override
    @Transactional
    public Treatment create(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        treatmentRepository.deleteById(id);
    }
}
