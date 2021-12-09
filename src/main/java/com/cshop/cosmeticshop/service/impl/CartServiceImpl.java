package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.repository.CartRepository;
import com.cshop.cosmeticshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author:Pave1Pal
 * Class implements CartService
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

//    @Override
//    public void calculatePrice(Cart cart) {
//        Long price = 0L;
//        for (Treatment treatment : cart.getTreatments()) {
//            price += treatment.getPrice();
//        }
//        cart.setTotalPrice(price);
//    }
}
