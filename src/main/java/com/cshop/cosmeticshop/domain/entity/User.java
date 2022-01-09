package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * User entity
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {


    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 40, message = "Incorrect last name")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @Size(min = 4, message = "Your password must be bigger than 4")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Order> orders;

    @OneToOne
    private Cart cart;
}
