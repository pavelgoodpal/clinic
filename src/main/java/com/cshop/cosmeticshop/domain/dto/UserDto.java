package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
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
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @Size(min = 4, message = "Your password must be bigger than 4")
    private String password;

    private Role role;

    private Status status;

    private List<Order> orders;

    private Cart cart;
}
