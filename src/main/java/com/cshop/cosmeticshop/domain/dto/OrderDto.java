package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * DTO class for Order
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Order", description = "Info about user")
public class OrderDto {

    @Schema(description = "Order id")
    private Long id;

    @Schema(description = "Phone number",
            required = true,
            pattern = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            maxLength = 12)
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @Schema(description = "Email",
            required = true,
            pattern = "email")
    @Email(message = "invalid email")
    private String email;

    @Schema(description = "Additional info")
    private String additionalInfo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finishAt;

    @Schema(description = "Order creation time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    @Schema(description = "Order last modified date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime lastModifiedDate;

    @Schema(description = "Cart which invoke on order")
    private Cart cart;

    @Schema(description = "User who made the order")
    private User user;

    private Doctor doctor;
}
