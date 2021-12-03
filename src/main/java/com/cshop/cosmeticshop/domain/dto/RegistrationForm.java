package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.userInfo.Role;
import com.cshop.cosmeticshop.domain.intity.userInfo.Status;
import com.cshop.cosmeticshop.domain.intity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @NotNull
    @Size(min=3)
    private String password;

    public RegistrationForm() {
    }

    public RegistrationForm(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

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
