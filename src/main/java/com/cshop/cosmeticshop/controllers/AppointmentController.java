package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.mapper.CartMapper;
import com.cshop.cosmeticshop.mapper.OrderMapper;
import com.cshop.cosmeticshop.service.DoctorService;
import com.cshop.cosmeticshop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.annotation.ServletSecurity;
import javax.validation.Valid;


/**
 * Controller to making order for appointment.
 *
 * @author Pave1Pal
 */
@Slf4j
@Controller
@ServletSecurity
@PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_ADMIN')")
@SessionAttributes({"treatment_order", "treatment_cart"})
@RequestMapping("appointment-orders")
@RequiredArgsConstructor
@Tag(name = "Appointment", description = "Controller manages appointment process")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
public class AppointmentController {

    private final OrderService orderService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    private final DoctorService doctorService;

    /**
     * Get method for start making order.
     *
     * @return html page with order form
     */
    @Operation(description = "Return order form in view")
    @ApiResponse(responseCode = "200", description = "Form view was returned")
    @GetMapping
    public String getFormOrder(Model model,
                               @PageableDefault(size = 10) Pageable pageable) {
        model.addAttribute("doctors", doctorService.getAllDoctors(pageable));
        return "appointment/order_form";
    }

    /**
     * Post method save order information.
     *
     * @param orderDto      order form comes from request
     * @param cartDto       cart with treatments comes from request
     * @param sessionStatus session status information
     * @param errors        errors in order form
     * @return If in form are errors return previous page. Return finish appointment page
     */
    @Operation(description = "Save order form")
    @ApiResponse(responseCode = "200", description = "Save order form and return finish appointment view")
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
