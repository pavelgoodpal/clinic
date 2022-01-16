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

    private Long id;
    private LocalDateTime startAt;
    private String email;
    private String userName;
    private String entity;
}
