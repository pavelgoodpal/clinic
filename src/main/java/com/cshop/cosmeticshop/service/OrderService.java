package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.User;

/**
 * @author:Pave1Pal
 * Service layer for Order entity
 */
public interface OrderService {

    /**
     * Save order in data storage. Input parameters is order, cart and user
     */
    Order saveOrder(Order order, Cart cart, User user);
}
