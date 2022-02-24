package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.service.CurrentUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for represent personal account information
 *
 * @author Pave1Pal
 */
@Controller
@RequiredArgsConstructor
@Tag(name = "Personal account", description = "Controller to manage personal account")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@RequestMapping("personal-account")
@PreAuthorize("permitAll()")
public class PersonalAccountController {

    private final CurrentUserService currentUserService;

    /**
     * Get method for home page of personal account.
     *
     * @param model for view
     * @return view with user information
     */
    @Operation(description = "get personal account page")
    @ApiResponse(responseCode = "200", description = "get home personal account page")
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("user", currentUserService.getUser());
        return "personal_account/home";
    }

}
