package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Model for convert needed info from Order to OrderPayload
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
public class PayloadDTO {

    /**
     * Factory method to create OrderPayload from Order
     * @param order Order
     * @return OrderPayload from order
     */
    static public PayloadDTO createFromOrder(Order order) {
        PayloadDTO payload = new PayloadDTO();
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
