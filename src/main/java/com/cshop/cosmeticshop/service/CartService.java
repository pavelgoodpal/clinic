package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;

/**
 * Service layer for Cart entity
 * @author Pave1Pal
 */
public interface CartService {

    /**
     * Save cart data storage
     * @param cart Cart
     * @return saved cart
     */
    Cart saveCart(Cart cart);
}
