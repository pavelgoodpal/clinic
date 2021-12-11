package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.User;

/**
 * @author:Pave1Pal
 * Service layer for User entity
 */
public interface UserService {

    /**
     * Find user by email
     * @param email email of user
     * @return found user
     */
    User findByEmail(String email);

    /**
     * Save user in data storage
     * @param user user is needed to save
     * @return saved user
     */
    User save(User user);
}
