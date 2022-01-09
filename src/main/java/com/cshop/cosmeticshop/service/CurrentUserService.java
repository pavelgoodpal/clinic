package com.cshop.cosmeticshop.service;


import com.cshop.cosmeticshop.domain.entity.User;

/**
 * Class for working with user and user principal from context
 */
public interface CurrentUserService {

    /**
     * Return user from SecurityContextHolder
     * @return authorized user
     */
    User getUser();

}
