package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.repository.OrderRepo;
import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.EmailService;
import com.cshop.cosmeticshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author:Pave1Pal
 * Class implements OrderService
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final EmailService emailService;


    public Order saveOrder(Order order, Cart cart, User user) {
        var savedCart = cartService.saveCart(cart);
        order.setCart(savedCart);
        order.findFinishTime();
        order.setUser(user);
        emailService.sendMessage(order, user);
        return orderRepo.save(order);
    }

}
