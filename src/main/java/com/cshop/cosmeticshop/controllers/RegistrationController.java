package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.RegistrationFormDto;
import com.cshop.cosmeticshop.domain.dto.UserDto;
import com.cshop.cosmeticshop.mapper.UserMapper;
import com.cshop.cosmeticshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * @author:Pave1Pal
 * Controller for user registration
 */
@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;


    /**
     * Get method return page with registration form
     */
    @GetMapping
    public String registrationForm(Model model, RegistrationFormDto form) {
        model.addAttribute("registration_form", form);
        return "registration/form";
    }

    /**
     * Post method handle registration form, if form data is invalid return page with previous form,
     * else return finish page
     */
    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute("registration_form") UserDto userDto,
                                      Errors errors) {
        if (errors.hasErrors())
            return "registration/form";
        userService.save(userMapper.UserDtoToUser(userDto));
        return "redirect:/registration/finish";
    }

    /**
     * Get method return finish page
     */
    @GetMapping("/finish")
    public String finishRegistration() {
        return "registration/finish";
    }

}
