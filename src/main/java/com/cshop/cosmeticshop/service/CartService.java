package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Cart;

/**
 * @author:Pave1Pal
 * Service layer for Cart entity
 */
public interface CartService {

    /**
     * Save cart in data storage using cart object
     */
    Cart saveCart(Cart cart);

    /**
     * Calculate total price of sum treatments prices
     */
    void calculatePrice(Cart cart);
}
