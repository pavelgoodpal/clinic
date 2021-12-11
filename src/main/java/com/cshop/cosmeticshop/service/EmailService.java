package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.User;

/**
 * Service layer for Email
 * @author Pave1Pal
 */
public interface EmailService {

    /**
     * Sending message using order information
     * @param order order data
     */
    void sendMessage(Order order);
}
