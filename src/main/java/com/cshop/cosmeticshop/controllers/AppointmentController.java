package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.mapper.CartMapper;
import com.cshop.cosmeticshop.mapper.OrderMapper;
import com.cshop.cosmeticshop.security.UserPrincipal;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.OrderService;
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
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequestMapping("appointment-order")
@RequiredArgsConstructor
public class AppointmentController {

    private final OrderService orderService;
    private final CartService cartService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

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
    public String submitOrder(@Valid @ModelAttribute("treatment_order") OrderDto orderDto,
                              @ModelAttribute("treatment_cart") CartDto cartDto,
                              SessionStatus sessionStatus,
                              Errors errors,
                              @AuthenticationPrincipal UserPrincipal userPrincipal) {

        if (errors.hasErrors()) {
            return "appointment/order_form";
        }
        var cart = cartService.saveCart(cartMapper.CartDtoToCart(cartDto));
        var order = orderMapper.orderDtoToOrder(orderDto);

        order.setCart(cart);
        order.setUser(userPrincipal.getUser());

        orderService.saveOrder(order);
        sessionStatus.setComplete();
        return "appointment/finish";
    }


}
