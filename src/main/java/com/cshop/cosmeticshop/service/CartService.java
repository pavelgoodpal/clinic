package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Cart;

public interface CartService {

    Cart saveCart(Cart cart);

    void calculatePrice(Cart cart);
}
