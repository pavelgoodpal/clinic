package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.repository.TreatmentRepo;
import com.cshop.cosmeticshop.service.TreatmentService;
import org.springframework.stereotype.Service;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepo treatmentRepo;

    public TreatmentServiceImpl(TreatmentRepo treatmentRepo) {
        this.treatmentRepo = treatmentRepo;
    }

    @Override
    public Iterable<Treatment> findAll() {
        return treatmentRepo.findAll();
    }

    @Override
    public Treatment findById(Long id) throws TreatmentNotFoundException {
        return treatmentRepo.findById(id).orElseThrow(() ->
                new TreatmentNotFoundException("Treatment not Found"));
    }

    @Override
    public Iterable<Treatment> addAll(Iterable<Treatment> entities) {
        return treatmentRepo.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        treatmentRepo.deleteById(id);
    }

    @Override
    public void delete(Treatment treatment) {
        treatmentRepo.delete(treatment);
    }

    @Override
    public Treatment update(Treatment treatment) {
        return null;
    }
}
