package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aspect for order service.
 *
 * @author PavelPa1
 */
@Aspect
@Component
@RequiredArgsConstructor
public class OrderAspect {

    private final CurrentUserService currentUserService;
    private final OutBoxService outBoxService;

    /**
     * Pointcut for saveOrder(...) method.
     */
    @Pointcut("execution(* com.cshop.cosmeticshop.service.OrderService.saveOrder(..))")
    public void createOrderPointCut() {
    }


    /**
     * Aspect for createOrderPointCut().
     *
     * @param order for outbox
     */
    @AfterReturning(pointcut = "createOrderPointCut()", returning = "order")
    public void afterOrderReturning(Order order) {
        order.setUser(currentUserService.getUser());
        outBoxService.buildOrderEmail(order);
    }

}
