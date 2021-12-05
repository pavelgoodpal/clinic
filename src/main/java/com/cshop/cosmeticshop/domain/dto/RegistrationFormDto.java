package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.constants.Role;
import com.cshop.cosmeticshop.domain.intity.constants.Status;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFormDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @NotNull
    @Size(min=3)
    private String password;

    public User toUser(PasswordEncoder encoder) {
        return new User
                (
                        firstName,
                        lastName,
                        email,
                        phoneNumber,
                        encoder.encode(password),
                        Role.USER,
                        Status.ACTIVE
                );
    }
}
