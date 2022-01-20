package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import com.cshop.cosmeticshop.repository.DoctorRepository;
import com.cshop.cosmeticshop.service.AdminService;
import com.cshop.cosmeticshop.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final AdminService adminService;
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setStatus(Status.ACTIVE);
        doctor.setRole(Role.DOCTOR);
        Doctor savedDoctor = doctorRepository.save(doctor);
        adminService.addDoctor(savedDoctor);
        return savedDoctor;
    }

    @Override
    public List<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable).getContent();
    }

    @Override
    public Doctor findById(Long id) throws DoctorNotFoundException {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found " + id));
    }
}
