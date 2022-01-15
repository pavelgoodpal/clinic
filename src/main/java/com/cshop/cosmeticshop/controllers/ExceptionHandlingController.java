package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception handler class
 * @author Pav1Pal
 */
@Slf4j
@Controller
public class ExceptionHandlingController {

    /**
     * Handel TreatmentNotFoundException
     * @param request of error
     * @param exception which is handled
     * @return view with exception info
     */
    @ExceptionHandler(TreatmentNotFoundException.class)
    public ModelAndView handleTreatmentNotFoundException(HttpServletRequest request, TreatmentNotFoundException exception) {
        log.info("Request: " + request.getRequestURI() + " raised " + exception);
        ModelAndView modelAndView = new ModelAndView("error/treatment_not_found");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURI());
        return modelAndView;
    }
}
