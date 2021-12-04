package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping
    public String showServices(Model model,
                               @PageableDefault(sort = {"price"}, direction = Sort.Direction.ASC) Pageable pageable,
                               Cart treatmentCart,
                               Order serviceOrder) {
       var page = treatmentService.findAll(pageable);
       model.addAttribute("treatment_cart", treatmentCart);
       model.addAttribute("treatment_order", serviceOrder);
       model.addAttribute("services", page);
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
