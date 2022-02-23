package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.domain.dto.TreatmentDto;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import com.cshop.cosmeticshop.mapper.CartMapper;
import com.cshop.cosmeticshop.mapper.TreatmentMapper;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.TreatmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 *
 * @author Pave1Pal
 */
@Controller
@Slf4j
@RequestMapping("/treatments")
@SessionAttributes({"treatment_order", "treatment_cart"})
@PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_DOCTOR')")
@RequiredArgsConstructor
@Tag(name = "Treatment", description = "Controller manages treatments")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Treatment not found")
public class TreatmentController {

    private final TreatmentMapper treatmentMapper;
    private final TreatmentService treatmentService;
    private final CartMapper cartMapper;
    private final CartService cartService;
    private final CurrentUserService currentUserService;

    /**
     * Method return Cart object in model which use in view
     *
     * @return cart object
     */
    @ModelAttribute("treatment_cart")
    public CartDto getTreatmentCart() {
        return cartMapper.toDto(cartService.findUserActiveCart());

    }

    /**
     * Get method return page with treatments and also provide opportunity to start an appointment
     *
     * @param model    model for view
     * @param order    order for start making appointment
     * @param pageable parameters of page
     * @return page with treatments
     */
    @Operation(description = "Find all treatments")
    @ApiResponse(responseCode = "200", description = "Treatments are founded")
    @GetMapping
    public String showTreatments(Model model, OrderDto order,
                                 @PageableDefault(sort = {"price"}, direction = Sort.Direction.ASC) Pageable pageable) {
        var page = treatmentService.findAll(pageable);
        model.addAttribute("user", currentUserService.getUser());
        model.addAttribute("treatments", page);
        model.addAttribute("treatment_order", order);
        return "/treatment/treatments";
    }


    /**
     * Get method return page with treatment info using its id
     *
     * @param id    treatment id
     * @param model model for view
     * @return page with treatment information
     * @throws TreatmentNotFoundException if treatment not found
     */
    @Operation(description = "Find treatment by id")
    @ApiResponse(responseCode = "200", description = "Treatment is founded")
    @GetMapping(path = "{id}")
    public String getTreatment(@PathVariable("id") Long id,
                               Model model) throws TreatmentNotFoundException {
        var treatment = treatmentService.findById(id);
        model.addAttribute("treatment", treatment);
        model.addAttribute("user", currentUserService.getUser());
        return "/treatment/treatment";
    }

    /**
     * Delete treatment by id.
     *
     * @param id of treatment
     * @return treatment/treatments view
     */
    @Operation(description = "Delete treatment by id")
    @ApiResponse(responseCode = "200", description = "Delete treatment")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/{id}/delete")
    public String deleteTreatment(@PathVariable("id") Long id) {
        treatmentService.delete(id);
        return "redirect:/treatments";
    }

    /**
     * Return view with form to create treatment.
     *
     * @return View with form to create treatment
     */
    @Operation(description = "Return view with creation treatment form")
    @ApiResponse(responseCode = "200", description = "Treatment form is sent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(path = "create")
    public String getFormToCreateTreatment() {
        return "treatment/create_treatment";
    }

    /**
     * Post method to create new treatment
     *
     * @param treatmentDto form
     * @param errors       in form
     * @return if has errors return treatment/create_treatment view, else redirect to treatments path
     */
    @Operation(description = "Create new treatment")
    @ApiResponse(responseCode = "200", description = "Treatment was created")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public String postTreatment(@Valid @ModelAttribute("treatment_form") TreatmentDto treatmentDto,
                                Errors errors) {
        if (errors.hasErrors()) {
            return "treatment/create_treatment";
        }
        treatmentService.create(treatmentMapper.fromDto(treatmentDto));
        return "redirect:/treatments";
    }

    /**
     * Post method to calculate total price of cart and return page to start appointment
     *
     * @param cartDto cart with treatments
     * @param model   model
     * @param errors  errors in cart
     * @return If cart has errors return treatments page. Else return page to start appointment
     */
    @Operation(description = "Save cart with chosen treatments and start appointment process returning order form view")
    @ApiResponse(responseCode = "200", description = "Treatment form is sended")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(path = "/appointment")
    public String appointmentProcess(@Valid @ModelAttribute("treatment_cart") CartDto cartDto,
                                     Model model,
                                     Errors errors) {
        if (errors.hasErrors()) {
            return "treatment/treatments";
        }
        var savedCart = cartService.saveActiveCart(cartMapper.fromDto(cartDto));
        var savedCartDto = cartMapper.toDto(savedCart);
        model.addAttribute("treatment_cart", savedCartDto);
        return "redirect:/appointment-orders";
    }
}
