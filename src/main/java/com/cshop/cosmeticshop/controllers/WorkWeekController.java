package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_DOCTOR', 'ROLE_ADMIN')")
@RequestMapping("work-weeks")
public class WorkWeekController {

    private final WorkWeekService workWeekService;

    @GetMapping(path = "/activation-code/{uuid}")
    public String activate(@PathVariable("uuid") UUID activationCode) {
        workWeekService.activate(activationCode);
        return "/work_week/activate";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oldWorkWeek", workWeekService.get(id));
        model.addAttribute("newWorkWeek", new WorkWeek());
        return "work_week/work_week";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newWorkWeek") WorkWeek newWorkWeek) {
        workWeekService.update(id, newWorkWeek);
        return "redirect:/work-weeks/updated";
    }

    @GetMapping("/updated")
    public String finishUpdate() {
        return "/work_week/updated";
    }

}
