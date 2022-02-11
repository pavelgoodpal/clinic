package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.validation.annotation.PhoneNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

/**
 * User entity
 *
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="d_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("user")
public class User extends BaseEntity {


    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 40, message = "Incorrect last name")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    @PhoneNumber
    private String phoneNumber;

    @Size(min = 4, message = "Your password must be bigger than 4")
    private String password;

    @Enumerated(value = STRING)
    private Role role;

    @Enumerated(value = STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Order> orders;

    @OneToOne
    private Cart cart;
}
