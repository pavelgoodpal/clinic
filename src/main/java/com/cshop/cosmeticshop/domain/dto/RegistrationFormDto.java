package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.constants.Role;
import com.cshop.cosmeticshop.domain.intity.constants.Status;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormDto {

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 40, message = "Incorrect last name")
    private String lastName;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "invalid email")
    private String email;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @NotNull
    @Size(min = 3)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                firstName,
                lastName,
                email,
                phoneNumber,
                password,
                role,
                status);
    }
}
