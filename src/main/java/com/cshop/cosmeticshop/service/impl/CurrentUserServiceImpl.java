package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Admin;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.exception.AdminNotFoundException;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import com.cshop.cosmeticshop.repository.AdminRepository;
import com.cshop.cosmeticshop.repository.DoctorRepository;
import com.cshop.cosmeticshop.security.UserPrincipal;
import com.cshop.cosmeticshop.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public User getUser() {
        var principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }

    @Override
    public Admin getAdmin() throws AdminNotFoundException {
        User user = getUser();
        return adminRepository.findById(user.getId())
                .orElseThrow(() -> new AdminNotFoundException("Admin not found " + user.getId()));
    }

    @Override
    public Doctor getDoctor() throws DoctorNotFoundException {
        User user = getUser();
        return doctorRepository.findById(user.getId())
                .orElseThrow(() -> new DoctorNotFoundException("Admin not found " + user.getId()));
    }
}
