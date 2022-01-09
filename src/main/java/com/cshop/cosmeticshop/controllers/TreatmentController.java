package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.mapper.CartMapper;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for displaying treatments and start making appointment
 * @author Pave1Pal
 */
@Controller
@Slf4j
@RequestMapping("/treatments")
@SessionAttributes({"treatment_order", "treatment_cart"})
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;
    private final CartMapper cartMapper;
    private final CartService cartService;

    /**
     * method return Cart object in model which use in view
     * @return cart object
     */
    @ModelAttribute("treatment_cart")
    public CartDto getTreatmentCart() {
        return cartMapper.toDto(cartService.findUserActiveCart());

    }

    /**
     * Get method return page with treatments and also provide opportunity to start an appointment
     * @param model model for view
     * @param order order for start making appointment
     * @param pageable parameters of page
     * @return page with treatments
     */
    @GetMapping
    public String showTreatments(Model model, OrderDto order,
                                 @PageableDefault(sort = {"price"}, direction = Sort.Direction.ASC) Pageable pageable) {
       var page = treatmentService.findAll(pageable);
       model.addAttribute("treatments", page);
       model.addAttribute("treatment_order", order);
        return "/treatments";
    }


    /**
     * Get method return page with treatment info using its id
     * @param id treatment id
     * @param model model for view
     * @return page with treatment information
     * @throws TreatmentNotFoundException if treatment not found
     */
    @GetMapping(path = "{id}")
    public String getTreatment(@PathVariable("id") Long id,
                               Model model) throws TreatmentNotFoundException {
        var treatment = treatmentService.findById(id);
        model.addAttribute("treatment", treatment);
        return "treatment";
    }

    /**
     * Post method to calculate total price of cart and return page to start appointment
     * @param cartDto cart with treatments
     * @param model model
     * @param errors errors in cart
     * @return If cart has errors return treatments page. Else return page to start appointment
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public String appointmentProcess(@Valid @ModelAttribute("treatment_cart") CartDto cartDto,
                                     Model model,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "/treatments";
        }
        var savedCart = cartService.saveActiveCart(cartMapper.fromDto(cartDto));
        var savedCartDto = cartMapper.toDto(savedCart);
        model.addAttribute("treatment_cart", savedCartDto);
        return "redirect:/appointment-orders";
    }
}
