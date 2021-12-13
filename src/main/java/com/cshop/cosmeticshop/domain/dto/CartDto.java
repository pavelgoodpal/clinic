package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO class for Cart
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;

    private Long totalPrice;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastModifiedTime;

    @NotEmpty(message = "choose treatment")
    private List<Treatment> treatments = new ArrayList<>();

    private User user;

    private CartStatus status;
}
