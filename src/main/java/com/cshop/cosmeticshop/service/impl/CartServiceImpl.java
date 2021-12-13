package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import com.cshop.cosmeticshop.repository.CartRepository;
import com.cshop.cosmeticshop.security.UserPrincipal;
import com.cshop.cosmeticshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implements CartService
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart saveActiveCart(Cart cart) {
        calculateTotalPrice(cart);
        cart.setStatus(CartStatus.ACTIVE);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateToNoActiveCart(Cart cart) {
        cart.setStatus(CartStatus.DONE);
        return cartRepository.save(cart);
    }

    @Override
    public Cart findUserActiveCart() {
        if (!(getAuthentication() instanceof AnonymousAuthenticationToken)) {
            var userPrincipal = getUserPrincipal();
            var carts = cartRepository.findCartByUserAndStatusOrderByCreationTimeDesc(
                    userPrincipal.getUser(), CartStatus.ACTIVE);
            if (!carts.isEmpty()) {
                return getFirstAndMakeAnotherDone(carts);
            }
        }
        return new Cart();
    }

    /**
     * Take UserPrincipal form SecurityContextHolder
     * @return UserPrincipal
     */
    private UserPrincipal getUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Take Authentication form SecurityContextHolder
     * @return Authentication
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Calculate total price of cart with treatments
     * @param cart with treatments
     */
    private void calculateTotalPrice(Cart cart) {
        long price = 0L;
        for (Treatment treatment : cart.getTreatments()) {
            price += treatment.getPrice();
        }
        cart.setTotalPrice(price);
    }

    /**
     * Return the earliest active cart, if carts list greater than one make another status carts done
     * @param carts active carts
     * @return the earliest active cart
     */
    private Cart getFirstAndMakeAnotherDone(List<Cart> carts) {
        if (carts.size() > 1) {
            Cart cart;
            for (int i = 1; i < carts.size(); i++) {
                cart = carts.get(i);
                cart.setStatus(CartStatus.DONE);
                cartRepository.save(cart);
            }
        }
        return carts.get(0);
    }
}
