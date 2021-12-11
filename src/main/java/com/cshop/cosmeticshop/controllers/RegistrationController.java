package com.cshop.cosmeticshop.controllers;

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
 * Controller for user registration
 * @author Pave1Pal
 */
@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;


    /**
     * Get method to start registration
     * @param model model object for MVC
     * @param form form for registration
     * @return form page
     */
    @GetMapping
    public String registrationForm(Model model, UserDto form) {
        model.addAttribute("registration_form", form);
        return "registration/form";
    }


    /**
     * Post method to save user in repository
     * @param form  registration form
     * @param errors errors in form
     * @return If form has errors return form page. Else return finish registration page
     */
    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute("registration_form") UserDto form,
                                      Errors errors) {
        if (errors.hasErrors())
            return "registration/form";
        userService.save(userMapper.UserDtoToUser(form));
        return "redirect:/registration/finish";
    }

    /**
     * Get method to return finish page
     * @return finish registration page
     */
    @GetMapping("/finish")
    public String finishRegistration() {
        return "registration/finish";
    }

}
