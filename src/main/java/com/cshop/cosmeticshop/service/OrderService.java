package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.User;

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
