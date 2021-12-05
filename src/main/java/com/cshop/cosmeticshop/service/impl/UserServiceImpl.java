package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.dto.RegistrationFormDto;
import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.repository.UserRepo;
import com.cshop.cosmeticshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author:Pave1Pal
 * Class implements UserService
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
         return userRepo.findByEmail(email).orElseThrow(() ->
                 new UsernameNotFoundException("User not found"));
    }

    @Override
    public User save(RegistrationFormDto form) {
        var user = userRepo.findByEmail(form.getEmail());
        return user.isEmpty() ? userRepo.save(form.toUser(passwordEncoder)) : user.get();
    }
}
