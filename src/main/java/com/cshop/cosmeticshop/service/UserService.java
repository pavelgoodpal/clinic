package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.dto.RegistrationFormDto;
import com.cshop.cosmeticshop.domain.intity.User;

/**
 * @author:Pave1Pal
 * Service layer for User entity
 */
public interface UserService {

    /**
     *Find user by email
     */
    User findByEmail(String email);

    /**
     * Save user form registration form
     */
    User save(RegistrationFormDto form);
}
