package com.cshop.cosmeticshop.domain.model;

import com.cshop.cosmeticshop.domain.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderPayload {

    static public OrderPayload createFromOrder(Order order) {
        OrderPayload payload = new OrderPayload();
        payload.setId(order.getId());
        payload.setEmail(order.getEmail());
        payload.setStartAt(order.getStartAt());
        payload.setUserName(order.getUser().getFirstName());
        payload.setEntity(Order.class.toString());
        return payload;
    }

    private Long id;
    private LocalDateTime startAt;
    private String email;
    private String userName;
    private String entity;
}
