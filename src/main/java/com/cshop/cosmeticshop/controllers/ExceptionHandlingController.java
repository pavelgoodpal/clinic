package com.cshop.cosmeticshop.controllers;

import com.cshop.cosmeticshop.exception.DoctorTimePeriodIsBusyException;
import com.cshop.cosmeticshop.exception.TreatmentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception handler class.
 *
 * @author Pav1Pal
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    /**
     * Handel TreatmentNotFoundException
     *
     * @param request   of error
     * @param exception which is handled
     * @return view with exception info
     */
    @ExceptionHandler(TreatmentNotFoundException.class)
    public ModelAndView handleTreatmentNotFoundException(HttpServletRequest request,
                                                         TreatmentNotFoundException exception) {
        log.info("Request: " + request.getRequestURI() + " raised " + exception);
        ModelAndView modelAndView = new ModelAndView("error/treatment_not_found");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURI());
        return modelAndView;
    }

    /**
     * Handle DoctorTimePeriodIsBusyException.
     *
     * @param request Error request
     * @param exception type of exception
     * @return view with exception information
     */
    @ExceptionHandler(DoctorTimePeriodIsBusyException.class)
    public ModelAndView handleDoctorTimePeriodIsBusyException(HttpServletRequest request,
                                                              DoctorTimePeriodIsBusyException exception) {
        log.info("Request: " + request.getRequestURI() + " raised " + exception);
        ModelAndView modelAndView = new ModelAndView("error/doctor_time_period_is_busy");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
