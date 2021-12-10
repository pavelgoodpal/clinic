package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.repository.OrderRepository;
import com.cshop.cosmeticshop.domain.entity.User;
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

    private final OrderRepository orderRepository;
    private final EmailService emailService;


    @Override
    public Order saveOrder(Order order) {
        findFinishTime(order);
        emailService.sendMessage(order);
        return orderRepository.save(order);
    }

    private void findFinishTime(Order order) {
        var finishTime = order.getStartAt();
        for (Treatment treatment : order.getCart().getTreatments()) {
            finishTime = finishTime.plusMinutes(treatment.getTreatmentTime());
        }
        order.setFinishAt(finishTime);
    }
}
