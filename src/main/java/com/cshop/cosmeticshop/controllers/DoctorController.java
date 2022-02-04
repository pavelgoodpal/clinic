package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.DoctorDto;
import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.mapper.DoctorMapper;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.service.CurrentUserService;
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
public class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final WorkWeekMapper workWeekMapper;
    private final CurrentUserService currentUserService;

    /**
     * Get method to register doctor.
     *
     * @param model for view
     * @return registration form
     */
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
    @GetMapping(path = "registration/finish")
    public String getFinishRegistration() {
        return "registration/finish";
    }

    /**
     * Get method returning view with doctors info.
     *
     * @param model
     * @param pageable parameters for finding doctors
     * @return view with doctors info
     */
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR', 'ROLE_ADMIN')")
    @GetMapping
    public String getDoctors(Model model,
                             @PageableDefault(sort = {"firstName"},
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        List<Doctor> doctors = doctorService.getAllDoctors(pageable);
        model.addAttribute("doctors", doctors);
        model.addAttribute("user", currentUserService.getUser());
        return "doctor/doctors";
    }

    /**
     * Get method for returning doctor view
     *
     * @param id of doctor
     * @param model
     * @return view with doctor info
     */
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
     * @param errors errors in form
     * @return finish view. If form has errors return view with form
     */
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

    /**
     * Get method returns form for doctor work week.
     *
     * @param model model for view
     * @return view with form to create work week schedule for doctor
     */
    @GetMapping(path = "{id}/work-week")
    public String getWorkWeekForm(Model model) {
        model.addAttribute("workWeek", new WorkWeek());
        return "/doctor/work_week";
    }

    /**
     * Post method for create work week for doctor.
     *
     * @param workWeekDto form containing work week info
     * @param id of doctor
     * @param errors in form
     * @return view with doctor info. If form has errors return previous form
     */
    @PostMapping(path = "{id}/work-week")
    public String postWorkWeek(@ModelAttribute("workWeek") WorkWeekDto workWeekDto,
                               @PathVariable("id") Long id,
                               Errors errors) {
        if(errors.hasErrors()) {
            return "/doctor/work_week";
        }
        WorkWeek workWeek = workWeekMapper.fromDto(workWeekDto);
        doctorService.setWorkWeekToDoctor(workWeek, id);
        return "redirect:/doctors/" + id;
    }
}
