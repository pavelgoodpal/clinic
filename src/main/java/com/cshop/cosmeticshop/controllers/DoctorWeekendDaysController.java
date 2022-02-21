package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.WeekendDayDto;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.mapper.WeekendDayMapper;
import com.cshop.cosmeticshop.service.DoctorService;
import com.cshop.cosmeticshop.service.WeekendDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller to manage weekend days of doctors
 *
 * @author PavelPa1
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("doctors")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class DoctorWeekendDaysController {

    private final DoctorService doctorService;
    private final WeekendDayMapper weekendDayMapper;
    private final WeekendDayService weekendDayService;

    /**
     * Get method with form for creating doctor weekend day by id
     *
     * @param doctorId id of doctor
     * @param model    model
     * @return form with weekend day fields
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') || hasPermission(#doctorId, 'authenticateByDoctorId')")
    @GetMapping(path = "{doctorId}/weekend-days/create-form")
    public String getCreateWeekendDayForm(@PathVariable Long doctorId, Model model) {
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("weekendDay", new WeekendDayDto());
        return "doctor/schedule/weekend_day/create_form";
    }

    /**
     * Post method for creating weekend day for doctor by his id
     *
     * @param doctorId doctor id
     * @param weekendDayDto weekend day form
     * @param errors errors in form
     * @return information about doctor if form without errors, else returns previous page
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN') || hasPermission(#doctorId, 'authenticateByDoctorId')")
    @PostMapping(path = "{doctorId}/weekend-days")
    public String createWeekendDay(@PathVariable Long doctorId,
                                   @ModelAttribute("weekendDay") WeekendDayDto weekendDayDto,
                                   Errors errors) {
        if (errors.hasErrors()) {
            return "doctor/schedule/weekend_day/create_form";
        }
        Optional.of(weekendDayDto)
                .map(weekendDayMapper::fromDto)
                .map(weekendDay -> doctorService.setDoctorWeekendDay(doctorId, weekendDay))
                .orElseThrow();
        return "redirect:/doctors/" + doctorId;
    }

    /**
     * Get method to get page of doctor weekends by his id
     *
     * @param doctorId doctor id
     * @param model model
     * @param pageable pageable
     * @return page with weekend days of doctor
     */
    @PreAuthorize("permitAll()")
    @GetMapping(path = "{doctorId}/weekend-days")
    public String getDoctorWeekendDays(@PathVariable Long doctorId, Model model, @PageableDefault(value = 10) Pageable pageable) {
        List<WeekendDay> weekendDays = weekendDayService.getAllDoctorWeekendDaysBy(doctorId, pageable);
        model.addAttribute("weekendDays", weekendDays);
        return "doctor/schedule/weekend_day/weekend_days";
    }

}
