package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.WeekendDayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("doctors")
public class DoctorWeekendDaysController {

    @GetMapping(path = "{doctorId}/weekend-days/create-form")
    public String createWeekendDay(@PathVariable Long doctorId, Model model) {
        model.addAttribute("weekendDay", new WeekendDayDto());
        return
    }
}
