package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.RegistrationFormDTO;
import com.cshop.cosmeticshop.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @ModelAttribute("registrationForm")
    public RegistrationFormDTO user() {
        return new RegistrationFormDTO();
    }

    @GetMapping
    public String registrationForm() {
        return "registrForm";
    }

    @PostMapping
    public String registrationProcess(@Valid RegistrationFormDTO form, Errors errors) {
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
