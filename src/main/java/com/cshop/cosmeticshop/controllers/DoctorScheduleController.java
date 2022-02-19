package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.service.DoctorScheduleService;
import com.cshop.cosmeticshop.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.ServletSecurity;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Controller
@ServletSecurity
@RequiredArgsConstructor
@RequestMapping("doctors")
public class DoctorScheduleController {

    private final DoctorScheduleService scheduleService;
    private final DoctorService doctorService;
    private final WorkWeekMapper workWeekMapper;

    /**
     * Get method returns form for creating doctor work week.
     *
     * @param model model for view
     * @return view with form to create work week schedule for doctor
     */
    @Operation(description = "Gets page with doctor work week form")
    @ApiResponse(responseCode = "200", description = "returns work week form for doctor")
    @GetMapping(path = "{doctorId}/work-week/create")
    public String getCreationDoctorWorkWeekForm(Model model, @PathVariable Long doctorId) {
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("workWeekForm", new WorkWeekDto());
        return "doctor/schedule/create_work_week";
    }

    /**
     * Post method for create work week for doctor.
     *
     * @param doctorId doctor id
     * @param workWeekDto form containing work week info
     * @param errors errors in form
     * @return view with doctor info. If form has errors return previous form
     */
    @Operation(description = "Create work week for doctor using doctor id")
    @ApiResponse(responseCode = "200", description = "Create doctor work week and return page with doctor info")
    @PostMapping(path = "{doctorId}/work-week")
    public String createDoctorWorkWeek(@PathVariable("doctorId") Long doctorId,
                                       @ModelAttribute("workWeekForm") WorkWeekDto workWeekDto,
                                       Errors errors) {
        if (errors.hasErrors()) {
            return "doctor/schedule/create_work_week";
        }
        Optional.ofNullable(workWeekDto)
                .map(workWeekMapper::fromDto)
                .map(workWeek -> doctorService.setDoctorWorkWeek(workWeek, doctorId))
                .orElseThrow();
        return "redirect:/doctors/" + doctorId;
    }

    @GetMapping(path = "{doctorId}/work-week")
    public String getDoctorWorkWeekToDate(Model model,
                                          @PathVariable(name = "doctorId") Long doctorId,
                                          @RequestParam(name = "year", defaultValue = "2022") String year,
                                          @RequestParam(name = "month", defaultValue = "2") String month,
                                          @RequestParam(name = "day", defaultValue = "19") String day) {
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        WorkWeek doctorWorkWeek = scheduleService.getWorkWeekByDoctorIdAndDate(doctorId, date);
        model.addAttribute("doctorWorkWeek", doctorWorkWeek);
        return "doctor/schedule/work_week";
    }


}
