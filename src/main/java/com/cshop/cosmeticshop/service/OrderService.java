package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.User;

/**
 * Service layer for Order entity
 * @author Pave1Pal
 */
public interface OrderService {

    /**
     * Save order in data storage
     * @param order Order
     * @return saved order
     */
    Order saveOrder(Order order, Cart cart);
}
