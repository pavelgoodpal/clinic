package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.dto.RegistrationForm;
import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.repository.UserRepo;
import com.cshop.cosmeticshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
         return userRepo.findByEmail(email).orElseThrow(() ->
                 new UsernameNotFoundException("User not found"));
    }

    @Override
    public User save(RegistrationForm form) {
        var user = userRepo.findByEmail(form.getEmail());
        if(user.isEmpty())
            return userRepo.save(form.toUser(passwordEncoder));
        return user.get();
    }
}
