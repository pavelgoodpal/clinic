package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.service.OutBoxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class OrderAspect {

    private final CurrentUserService currentUserService;
    private final OutBoxService outBoxService;

    @Pointcut("execution(* com.cshop.cosmeticshop.service.OrderService.saveOrder(..))")
    public void createOrderPointCut() {
    }


    @AfterReturning(value = "createOrderPointCut() && args(order, cart)")
    public void after(Order order, Cart cart) throws JsonProcessingException {
        order.setUser(currentUserService.getUser());
        outBoxService.save(order);
    }

}
