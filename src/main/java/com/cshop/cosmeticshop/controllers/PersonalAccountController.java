package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.service.CurrentUserService;
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
@RequestMapping("personal-account")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')")
public class PersonalAccountController {

    private final CurrentUserService currentUserService;

    /**
     * Get method for home page of personal account.
     *
     * @param model for view
     * @return view with user information
     */
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("user", currentUserService.getUser());
        return "personal_account/home";
    }

}
