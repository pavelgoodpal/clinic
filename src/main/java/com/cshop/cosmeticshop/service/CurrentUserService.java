package com.cshop.cosmeticshop.service;


import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.User;
import lombok.SneakyThrows;

/**
 * Class for working with user and user principal from context.
 *
 * @author Pave1Pal
 */
public interface CurrentUserService {

    /**
     * Get user from SecurityContextHolder.
     *
     * @return authorized user
     */
    User getUser();

    /**
     * Get doctor form SecurityContextHolder
     *
     * @return doctor
     */
    @SneakyThrows
    Doctor getDoctor();
}
