package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.CartDTO;
import com.cshop.cosmeticshop.domain.dto.TreatmentDTO;
import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.service.OrderService;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequestMapping("services_order")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String formOrder() {
        return "service/order_form";
    }

    @PostMapping
    public String submitOrder(@Valid @ModelAttribute("services_order") Order order,
                              @ModelAttribute("treatment_cart") Cart cart,
                              SessionStatus sessionStatus,
                              Errors errors,
                              @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "service/order_form";
        }

        orderService.saveOrder(order, cart, user);
        sessionStatus.setComplete();
        return "service/finish";
    }


}
