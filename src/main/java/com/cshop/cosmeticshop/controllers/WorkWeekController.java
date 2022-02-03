package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("work-weeks")
public class WorkWeekController {

    private final WorkWeekService workWeekService;

    @GetMapping(path = "/activation-code/{uuid}")
    public String activate(@PathVariable("uuid") UUID activationCode) {
        workWeekService.activate(activationCode);
        return "/work_week/activate";
    }
}
