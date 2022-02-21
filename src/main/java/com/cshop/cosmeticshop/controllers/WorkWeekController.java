package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.domain.dto.WorkWeekDto;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.mapper.WorkWeekMapper;
import com.cshop.cosmeticshop.service.WorkWeekService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller to manage work weeks
 *
 * @author Pave1Pal
 */
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@RequestMapping("work-weeks")
@Tag(name = "WorkWeek", description = "Controller manages doctor work week")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
public class WorkWeekController {

    private final WorkWeekMapper workWeekMapper;
    private final WorkWeekService workWeekService;

    /**
     * Get method to activate work week using activation code in path
     *
     * @param activationCode which activate work week
     * @return view of success activation
     */
    @Operation(description = "Activate doctor work week")
    @ApiResponse(responseCode = "200", description = "returns activation work week page")
    @PreAuthorize("permitAll()")
    @GetMapping(path = "/activation-code/{uuid}")
    public String activate(@PathVariable("uuid") UUID activationCode) {
        workWeekService.activate(activationCode);
        return "/work_week/activate";
    }

    /**
     * Get method returns view with work week.
     *
     * @param id    of work week
     * @param model for view
     * @return view with work week info
     */
    @Operation(description = "Get doctor work week in page by work week id")
    @ApiResponse(responseCode = "200", description = "Returns work week info")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping("/{id}/update")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional.of(id)
                .map(workWeekService::get)
                .map(workWeekMapper::toDto)
                .map(workWeekDto -> model.addAttribute("oldWorkWeek", workWeekDto))
                .orElseThrow();
        model.addAttribute("newWorkWeek", new WorkWeekDto());
        return "doctor/schedule/work_week/update";
    }

    /**
     * Post method for updating work week data.
     *
     * @param id          of updated work week
     * @param newWorkWeek form with updated fields
     * @return view of successful update
     */
    @Operation(description = "Update work week using its id")
    @ApiResponse(responseCode = "200", description = "returns updated page")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("newWorkWeek") WorkWeek newWorkWeek) {
        Optional.of(newWorkWeek)
                .map(workWeek -> workWeekService.update(id, workWeek))
                .orElseThrow();
        return "redirect:/work-weeks/updated";
    }

    /**
     * Get method returning successful work week update view.
     *
     * @return successful work week update view
     */
    @Operation(description = "Returns finish update page")
    @ApiResponse(responseCode = "200", description = "returns finish update page")
    @GetMapping("/updated")
    public String finishUpdate() {
        return "/work_week/updated";
    }
}
