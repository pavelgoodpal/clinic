package com.cshop.cosmeticshop.aspect;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class OrderAspect {

    private final EmailService emailService;

    @Pointcut("execution(* com.cshop.cosmeticshop.service.OrderService.saveOrder(..))")
    public void createOrderPointCut() {
    }


    @After(value = "createOrderPointCut() && args(order, cart)", argNames = "order,cart")
    public void after(Order order, Cart cart) {
        emailService.sendOrderMessage(order);
    }

}
