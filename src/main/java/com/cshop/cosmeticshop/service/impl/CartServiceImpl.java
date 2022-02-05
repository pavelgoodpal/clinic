package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import com.cshop.cosmeticshop.repository.CartRepository;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Class implements CartService
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CurrentUserService authService;
    private final UserService userService;

    @Override
    public Cart saveActiveCart(Cart cart) {
        calculateTotalPrice(cart);
        cart.setStatus(CartStatus.ACTIVE);
        var savedCart = cartRepository.save(cart);
        userService.updateUserCart(savedCart);
        return savedCart;
    }

    @Override
    public Cart updateToNoActiveCart(Cart cart) {
        cart.setStatus(CartStatus.DONE);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findUserActiveCart() {
        var cart = cartRepository.findCartByUserAndStatus(authService.getUser(), CartStatus.ACTIVE);
        return cart.isEmpty() ? new Cart() : cart.get();
    }

    /**
     * Calculate total price of cart with treatments.
     *
     * @param cart with treatments
     */
    private Long calculateTotalPrice(Cart cart) {
        var totalPrice = cart.getTreatments().stream().mapToLong(Treatment::getPrice).sum();
        cart.setTotalPrice(totalPrice);
        return totalPrice;
    }
}
