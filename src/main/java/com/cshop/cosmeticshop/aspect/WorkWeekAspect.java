package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
    @Pointcut("execution(* com.cshop.cosmeticshop.service.DoctorService.setWorkWeekToDoctor(..))")
    public void setWorkWeekToDoctorPointCut() {
    }

    /**
     * Pointcut for update method
     */
    @Pointcut("execution(* com.cshop.cosmeticshop.service.WorkWeekService.update(..))")
    public void updateWorkWeekPointCut(){
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
     * After returning aspect for updateWorkWeekPointCut() method.
     *
     * @param workWeek of doctor
     */
    @AfterReturning(pointcut = "updateWorkWeekPointCut()", returning = "workWeek")
    public void afterReturningUpdate(WorkWeek workWeek) {
        if (currentUserService.getUser().getRole().getRole().equals("ROLE_ADMIN")) {
            outBoxService.buildUpdateWorkWeekEmailForDoctor(workWeek);
        } else {
            outBoxService.buildUpdateWorkWeekEmailForAdmins(workWeek);
        }

    }

}
