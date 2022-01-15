package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Cart", description = "Info about cart")
public class CartDto {

    @Schema(description = "Cart id")
    private Long id;

    @Schema(description = "Total price of cart")
    private Long totalPrice;

    @Schema(description = "Time of creation cart")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    @Schema(description = "Last modified time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastModifiedTime;

    @Schema(description = "List of treatments",
            required = true,
            minLength = 1)
    @NotEmpty(message = "choose treatment")
    private List<Treatment> treatments = new ArrayList<>();

    @Schema(description = "User who created the cart")
    private User user;

    @Schema(description = "Cart status")
    private CartStatus status;
}
