package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.User;

/**
 * Service layer for User entity
 *
 * @author: Pave1Pal
 */
public interface UserService {

    /**
     * Find user by email
     *
     * @param email email of user
     * @return found user
     */
    User findByEmail(String email);

    /**
     * Save user in data storage
     *
     * @param user user is needed to save
     * @return saved user
     */
    User create(User user);

    /**
     * Update information about user cart. User takes form SecurityContextHolder.
     *
     * @param cart user cart
     * @return updated user
     */
    User updateUserCart(Cart cart);
}
