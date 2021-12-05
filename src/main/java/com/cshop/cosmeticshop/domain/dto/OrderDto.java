package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private String phoneNumber;

    private String email;

    private String additionalInfo;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    private LocalDateTime creationTime;

    private Cart cart;

    private User user;
}
