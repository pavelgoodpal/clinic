package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Aspect for creating and updating work week info.
 *
 * @author Pave1Pal
 */
@Aspect
@Component
@RequiredArgsConstructor
public class WorkWeekAspect {

    private final OutBoxService outBoxService;
    private final CurrentUserService currentUserService;

    /**
     * Pointcut for setWorkWeekToDoctor method
     */
    @Pointcut("execution(* com.cshop.cosmeticshop.service.DoctorService.setDoctorWorkWeek(..))")
    public void setWorkWeekToDoctorPointCut() {
    }

    /**
     * After returning aspect for setWorkWeekToDoctorPointCut() method.
     *
     * @param workWeek of doctor
     */
    @AfterReturning(pointcut = "setWorkWeekToDoctorPointCut()", returning = "workWeek")
    public void afterReturningCreate(WorkWeek workWeek){
        outBoxService.buildCreateWorkWeekEmail(workWeek);
    }

    /**
     * Pointcut for update method
     */
    @Pointcut("execution(* com.cshop.cosmeticshop.service.WorkWeekService.update(..))")
    public void updateWorkWeekPointCut(){
    }

    /**
     * After returning aspect for updateWorkWeekPointCut() method.
     *
     * @param workWeek of doctor
     */
    @AfterReturning(pointcut = "updateWorkWeekPointCut()", returning = "workWeek")
    public void afterReturningUpdate(WorkWeek workWeek) {
        if (currentUserService.getUser().getRole() == Role.ADMIN) {
            outBoxService.buildUpdateWorkWeekEmailForDoctor(workWeek);
        } else {
            outBoxService.buildUpdateWorkWeekEmailForAdmins(workWeek);
        }

    }

    /**
     * Pointcut for activate work week method
     */
    @Pointcut("execution(* com.cshop.cosmeticshop.service.WorkWeekService.activate(..))")
    public void activateWorkWeek(){
    }

    /**
     * Before advice for activateWorkWeek pointcut. Take UUID activation code from method
     * and build outbox.
     *
     * @param activationCode UUID activation code
     */
    @Before(value = "activateWorkWeek() && args(activationCode)")
    public void beforeActivateWorkWeek(UUID activationCode) {
        if (currentUserService.getUser().getRole() == Role.ADMIN) {
            outBoxService.buildActivateWorkWeekEmailForDoctor(activationCode);
        } else {
            outBoxService.buildActivateWorkWeekEmailForAdmins(activationCode);
        }
    }

}
