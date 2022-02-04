package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("personal-account")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')")
public class PersonalAccountController {

    private final CurrentUserService currentUserService;

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("user", currentUserService.getUser());
        return "personal_account/home";
    }

}
