package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.repository.CartRepo;
import com.cshop.cosmeticshop.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void calculatePrice(Cart cart) {
        Long price = 0L;
        for (Treatment treatment : cart.getTreatments()) {
            price += treatment.getPrice();
        }
        cart.setTotalPrice(price);
    }
}
