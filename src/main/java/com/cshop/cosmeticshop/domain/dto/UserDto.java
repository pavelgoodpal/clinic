package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.validation.annotation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * DTO class for User
 *
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "User", description = "Info about user")
public class UserDto {

    @Schema(description = "User id")
    private Long id;

    @Schema(description = "First name",
            required = true)
    @NotBlank(message = "first name is required")
    private String firstName;

    @Schema(description = "Last name",
            required = true)
    @NotBlank(message = "last name is required")
    private String lastName;

    @Schema(description = "Email",
            required = true,
            pattern = "email")
    @Email(message = "invalid email")
    private String email;

    @Schema(description = "Phone number",
            required = true,
            pattern = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            maxLength = 12)
    @PhoneNumber
    private String phoneNumber;

    @Schema(description = "Password",
            required = true,
            minLength = 4)
    @Size(min = 4, message = "Your password must be bigger than 4")
    private String password;

    @Schema(description = "Role")
    private Role role;

    @Schema(description = "Status")
    private Status status;

    @Schema(description = "List of orders")
    private List<Order> orders;

    @Schema(description = "Cart")
    private Cart cart;
}
