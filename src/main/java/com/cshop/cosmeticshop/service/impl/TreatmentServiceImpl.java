package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.repository.TreatmentRepository;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author:Pave1Pal
 * Class implements TreatmentService
 */
@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Override
    public Iterable<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    @Override
    public Page<Treatment> findAll(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }

    @Override
    public Treatment findById(Long id) throws TreatmentNotFoundException {
        return treatmentRepository.findById(id).orElseThrow(() ->
                new TreatmentNotFoundException("Treatment not Found"));
    }

    @Override
    public Iterable<Treatment> addAll(Iterable<Treatment> entities) {
        return treatmentRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        treatmentRepository.deleteById(id);
    }

    @Override
    public void delete(Treatment treatment) {
        treatmentRepository.delete(treatment);
    }

    @Override
    public Treatment update(Treatment treatment) {
        return null;
    }
}
