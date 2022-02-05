package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.UserDto;
import com.cshop.cosmeticshop.mapper.UserMapper;
import com.cshop.cosmeticshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 *
 * @author Pave1Pal
 */
@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
@Tag(name = "Registration", description = "Controller manages registration process")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;


    /**
     * Get method to start registration
     *
     * @param model model object for MVC
     * @param form  form for registration
     * @return form page
     */
    @Operation(description = "Return registration form in view")
    @ApiResponse(responseCode = "200", description = "Form view was returned")
    @GetMapping
    public String registrationForm(Model model, UserDto form) {
        model.addAttribute("registration_form", form);
        return "registration/form";
    }


    /**
     * Post method to save user in repository
     *
     * @param form   registration form
     * @param errors errors in form
     * @return If form has errors return form page. Else return finish registration page
     */
    @Operation(description = "Save registration form")
    @ApiResponse(responseCode = "200", description = "Save registration form and return finish registration view")
    @PostMapping
    public String registrationProcess(@Valid @ModelAttribute("registration_form") UserDto form,
                                      Errors errors) {
        if (errors.hasErrors())
            return "registration/form";
        userService.create(userMapper.fromDto(form));
        return "redirect:/registration/finish";
    }

    /**
     * Get method to return finish page
     *
     * @return finish registration page
     */
    @Operation(description = "Return finish registration view")
    @ApiResponse(responseCode = "200", description = "Finish registration view was returned")
    @GetMapping("/finish")
    public String finishRegistration() {
        return "registration/finish";
    }

}
