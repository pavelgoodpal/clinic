package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.repository.OrderRepository;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.OrderService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 * Class implements OrderService
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OutBoxService outBoxService;


    @Override
    @SneakyThrows
    @Transactional
    public Order saveOrder(Order order, Cart cart) {
        var updatedCart = cartService.updateToNoActiveCart(cart);
        order.setCart(updatedCart);
        calculateFinishTime(order);
        Order savedOrder = orderRepository.save(order);
        outBoxService.save(savedOrder);
        return savedOrder;
    }

    /**
     * Calculate time when treatments will end using order data
     * @param order Order information
     */
    private void calculateFinishTime(Order order) {
        order.setFinishAt(
                Optional.of(order)
                .map(Order::getCart)
                .map(Cart::getTreatments)
                .stream()
                .flatMap(Collection::stream)
                .map(Treatment::getTreatmentTime)
                .reduce(order.getStartAt(), LocalDateTime::plusMinutes,
                        (r,l) -> r)
        );
    }
}
