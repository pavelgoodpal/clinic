package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.domain.intity.constants.Role;
import com.cshop.cosmeticshop.domain.intity.constants.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private Role role;

    private Status status;


    public static User toUser() {
        return new User();
    }
}
