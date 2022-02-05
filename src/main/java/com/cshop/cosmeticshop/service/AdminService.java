package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.User;

import java.util.List;

/**
 * Service to manage admins.
 *
 * @author Pave1Pal
 */
public interface AdminService {

    /**
     * Get all admins.
     *
     * @return list of all admins
     */
    List<User> getAllAdmins();
}
