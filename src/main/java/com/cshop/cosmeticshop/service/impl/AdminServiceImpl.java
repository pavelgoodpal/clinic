package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Admin;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.repository.AdminRepository;
import com.cshop.cosmeticshop.repository.DoctorRepository;
import com.cshop.cosmeticshop.service.AdminService;
import com.cshop.cosmeticshop.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final CurrentUserService currentUserService;

    @Override
    @SneakyThrows
    public void addDoctor(Doctor doctor) {
        Admin admin = currentUserService.getAdmin();
        List<Doctor> doctors = admin.getDoctors();
        doctors.add(doctor);
        admin.setDoctors(doctors);
        adminRepository.save(admin);
    }
}
