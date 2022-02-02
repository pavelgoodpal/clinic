package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class WorkWeekAspect {

    private final OutBoxService outBoxService;

    @Pointcut("execution(* com.cshop.cosmeticshop.service.DoctorService.setWorkWeekToDoctor(..))")
    public void setWorkWeekToDoctorPointCut() {
    }

    @AfterReturning(pointcut = "setWorkWeekToDoctorPointCut()", returning = "workWeek")
    public void afterReturning(WorkWeek workWeek){
        outBoxService.buildWorkWeekEmail(workWeek);
    }
}
