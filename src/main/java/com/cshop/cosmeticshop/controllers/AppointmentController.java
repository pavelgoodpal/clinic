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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.annotation.ServletSecurity;
import javax.validation.Valid;


/**
 * Controller to making order for appointment.
 * @author Pave1Pal
 */
@Slf4j
@Controller
@ServletSecurity
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequestMapping("appointment-orders")
@RequiredArgsConstructor
public class AppointmentController {

    private final OrderService orderService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    /**
     * Get method for start making order.
     * @return html page with order form
     */
    @GetMapping
    public String formOrder() {
        return "appointment/order_form";
    }

    /**
     * Post method save order information.
     * @param orderDto order form comes from request
     * @param cartDto cart with treatments comes from request
     * @param sessionStatus session status information
     * @param errors errors in order form
     * @return If in form are errors return previous page. Return finish appointment page
     */
    @PostMapping
    public String submitOrder(@Valid @ModelAttribute("treatment_order") OrderDto orderDto,
                              @ModelAttribute("treatment_cart") CartDto cartDto,
                              SessionStatus sessionStatus,
                              Errors errors) {

        if (errors.hasErrors()) {
            return "appointment/order_form";
        }
        var cart = cartMapper.fromDto(cartDto);
        var order = orderMapper.fromDto(orderDto);

        orderService.saveOrder(order, cart);
        sessionStatus.setComplete();
        return "appointment/finish";
    }
}
