package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.OrderService;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.annotation.ServletSecurity;
import javax.validation.Valid;

/**
 * @author:Pave1Pal
 * Controller to making order for appointment.
 */
@Slf4j
@Controller
@ServletSecurity
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequestMapping("appointment-order")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    /**
     * Get method return page with form fo filling order data
     **/
    @GetMapping
    public String formOrder() {
        return "appointment/order_form";
    }

    /**
     * Post method handle order form and cart with treatments.
     * If order form is invalid return /appointment/order_form.
     * If validation is successful method save data about order and cart
     * in repository.
     * Finally, method return appointment finish page in response.
     **/
    @PostMapping
    public String submitOrder(@Valid @ModelAttribute("treatment_order") Order order,
                              @ModelAttribute("treatment_cart") Cart cart,
                              SessionStatus sessionStatus,
                              Errors errors,
                              @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "appointment/order_form";
        }

        var savedCart = cartService.saveCart(cart);
        orderService.saveOrder(order, savedCart, user);
        sessionStatus.setComplete();
        return "appointment/finish";
    }


}
