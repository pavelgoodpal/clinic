package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Treatment;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/services")
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final CartService cartService;

    @ModelAttribute("treatment_cart")
    public Cart serviceCart() {
        return new Cart();
    }

    @ModelAttribute("services")
    public Iterable<Treatment> allServices() {
        return treatmentService.findAll();
    }

    @ModelAttribute("treatment_order")
    public Order serviceOrder() {
        return new Order();
    }

    @GetMapping
    public String showServices() {
        return "service/services";
    }

    @PostMapping
    public String appointmentProcess(@Valid @ModelAttribute("treatment_cart") Cart treatmentCart,
                                     @ModelAttribute("treatment_order") Order serviceOrder,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "service/services";
        }
        cartService.calculatePrice(treatmentCart);
        return "redirect:/services_order";
    }
}
