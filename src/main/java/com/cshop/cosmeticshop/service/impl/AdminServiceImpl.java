package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.repository.UserRepository;
import com.cshop.cosmeticshop.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAllAdmins() {
        return userRepository.findByRole(Role.ADMIN);
    }
}
