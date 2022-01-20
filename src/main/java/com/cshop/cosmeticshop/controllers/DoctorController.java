package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.DoctorDto;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import com.cshop.cosmeticshop.mapper.DoctorMapper;
import com.cshop.cosmeticshop.service.DoctorService;
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

@Slf4j
@Controller
@ServletSecurity
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@RequestMapping("doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;

    @GetMapping(path = "registration")
    public String getRegistrationForm() {
        return "registration/doctor_form";
    }

    @GetMapping(path = "registration/finish")
    public String getFinishRegistration() {
        return "registration/finish";
    }

    @GetMapping
    public String getDoctors(Model model,
                             @PageableDefault(sort = {"first_name"},
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        List<Doctor> doctors = doctorService.getAllDoctors(pageable);
        model.addAttribute("doctors", doctors);
        return "doctor/doctors";
    }

    @GetMapping(path = "{id}")
    public String getDoctor(@PathVariable("id") Long id, Model model) throws DoctorNotFoundException {
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctor/doctor";
    }

    @PostMapping
    public String postDoctor(@Valid @ModelAttribute("doctor") DoctorDto doctorDto, Errors errors) {
        if (errors.hasErrors()) {
            return "registration/doctor_form";
        }
        doctorService.create(doctorMapper.fromDto(doctorDto));
        return "redirect:doctors/registration/finish";
    }
}
