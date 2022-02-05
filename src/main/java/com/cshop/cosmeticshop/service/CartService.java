package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;

/**
 * Service layer for Cart entity.
 *
 * @author Pave1Pal
 */
public interface CartService {

    /**
     * Save active cart in data storage.
     *
     * @param cart Cart
     * @return saved cart
     */
    Cart saveActiveCart(Cart cart);

    /**
     * Find last active user cart.
     *
     * @return Active cart
     */
    Cart findUserActiveCart();

    /**
     * Save no active cart in data storage.
     *
     * @param cart is not active
     * @return saved no active cart
     */
    Cart updateToNoActiveCart(Cart cart);
}
