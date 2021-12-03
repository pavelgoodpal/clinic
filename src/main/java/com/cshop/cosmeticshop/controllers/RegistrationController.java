package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.RegistrationForm;
import com.cshop.cosmeticshop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm user() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registrationForm() {
        return "registrForm";
    }

    @PostMapping
    public String registrationProcess(@Valid RegistrationForm form, Errors errors) {
        if (errors.hasErrors())
            return "registrForm";
        userService.save(form);
        return "redirect:/registration/finish";
    }

    @GetMapping("/finish")
    public String finishRegistration() {
        return "/finishRegist";
    }

}
