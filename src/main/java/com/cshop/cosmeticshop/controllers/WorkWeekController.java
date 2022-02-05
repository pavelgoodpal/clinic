package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller to manage work weeks
 *
 * @author Pave1Pal
 */
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_DOCTOR', 'ROLE_ADMIN')")
@RequestMapping("work-weeks")
public class WorkWeekController {

    private final WorkWeekService workWeekService;

    /**
     * Get method to activate work week using activation code in path
     *
     * @param activationCode which activate work week
     * @return view of success activation
     */
    @GetMapping(path = "/activation-code/{uuid}")
    public String activate(@PathVariable("uuid") UUID activationCode) {
        workWeekService.activate(activationCode);
        return "/work_week/activate";
    }

    /**
     * Get method returns view with work week.
     *
     * @param id of work week
     * @param model for view
     * @return view with work week info
     */
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oldWorkWeek", workWeekService.get(id));
        model.addAttribute("newWorkWeek", new WorkWeek());
        return "work_week/work_week";
    }

    /**
     * Post method for updating work week data.
     *
     * @param id of updated work week
     * @param newWorkWeek form with updated fields
     * @return view of successful update
     */
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newWorkWeek") WorkWeek newWorkWeek) {
        workWeekService.update(id, newWorkWeek);
        return "redirect:/work-weeks/updated";
    }

    /**
     * Get method returning successful work week update view.
     *
     * @return successful work week update view
     */
    @GetMapping("/updated")
    public String finishUpdate() {
        return "/work_week/updated";
    }

}
