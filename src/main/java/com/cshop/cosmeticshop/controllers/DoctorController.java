package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.DoctorDto;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.mapper.DoctorMapper;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.ServletSecurity;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller to manage doctor info.
 *
 * @author Pave1Pal
 */
@Slf4j
@Controller
@ServletSecurity
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_DOCTOR')")
@RequestMapping("doctors")
@RequiredArgsConstructor
@Tag(name = "Doctor", description = "Controller manages doctor")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
public class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final CurrentUserService currentUserService;

    /**
     * Get method to register doctor.
     *
     * @param model for view
     * @return registration form
     */
    @Operation(description = "Returns registration doctor from in view")
    @ApiResponse(responseCode = "200", description = "Returns registration doctor form view")
    @GetMapping(path = "registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "registration/doctor_form";
    }

    /**
     * Get method returning finish registration view.
     *
     * @return registration finish view
     */
    @Operation(description = "Returns finish registration view")
    @ApiResponse(responseCode = "200", description = "Returns finish registration view")
    @GetMapping(path = "registration/finish")
    public String getFinishRegistration() {
        return "registration/finish";
    }

    /**
     * Get method returning view with doctors info.
     *
     * @param model    model for view
     * @param pageable parameters for finding doctors
     * @return view with doctors info
     */
    @Operation(description = "Returns page with doctors")
    @ApiResponse(responseCode = "200", description = "Returns page with doctors")
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR', 'ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @GetMapping
    public String getDoctors(Model model, @PageableDefault(sort = {"firstName"},
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        List<Doctor> doctors = doctorService.getAllDoctors(pageable);
        model.addAttribute("doctors", doctors);
        model.addAttribute("user", currentUserService.getUser());
        return "doctor/doctors";
    }

    /**
     * Get method for returning doctor view
     *
     * @param id    of doctor
     * @param model model for view
     * @return view with doctor info
     */
    @Operation(description = "Returns doctor info by id")
    @ApiResponse(responseCode = "200", description = "Returns found doctor")
    @GetMapping(path = "{id}")
    public String getDoctor(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctor/doctor";
    }

    /**
     * Post method to create new doctor.
     *
     * @param doctorDto doctor form
     * @param errors    errors in form
     * @return finish view. If form has errors return view with form
     */
    @Operation(description = "Create doctor")
    @ApiResponse(responseCode = "200", description = "Creates new doctor")
    @PostMapping
    public String postDoctor(@Valid @ModelAttribute("doctor") DoctorDto doctorDto,
                             Errors errors) {
        if (errors.hasErrors()) {
            return "registration/doctor_form";
        }
        Optional.ofNullable(doctorDto)
                .map(doctorMapper::fromDto)
                .map(doctorService::create)
                .orElseThrow();
        return "redirect:doctors/registration/finish";
    }
}
