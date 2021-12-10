package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.User;

/**
 * @author:Pave1Pal
 * Service layer for Email
 */
public interface EmailService {

    /**
     * Sending message using order and user information
     */
    void sendMessage(Order order);
}
