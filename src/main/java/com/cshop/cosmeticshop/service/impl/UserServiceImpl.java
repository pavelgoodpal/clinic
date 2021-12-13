package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
         return userRepository.findByEmail(email).orElseThrow(() ->
                 new UsernameNotFoundException("User not found"));
    }

    @Override
    public User create(User newUser) {
        var foundedUser = userRepository.findByEmail(newUser.getEmail());
        if (foundedUser.isEmpty()) {
            newUser.setRole(Role.USER);
            newUser.setStatus(Status.ACTIVE);
            var encodedPassword = passwordEncoder.encode(newUser.getPassword());
            newUser.setPassword(encodedPassword);
            return userRepository.save(newUser);
        }
        return foundedUser.get();
    }
}
