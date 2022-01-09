package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;

/**
 * Service layer for Email
 * @author Pave1Pal
 */
public interface EmailService {

    /**
     * Sending message using order information
     * @param order order data
     */
    void sendOrderMessage(Order order);
}
