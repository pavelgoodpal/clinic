package com.cshop.cosmeticshop.domain.intity;

import com.cshop.cosmeticshop.domain.intity.constants.Role;
import com.cshop.cosmeticshop.domain.intity.constants.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
public class User extends Identifier implements UserDetails {


    @NonNull
    @NotBlank(message = "first name is required")
    private String firstName;

    @NonNull
    @NotBlank(message = "last name is required")
    @Size(min = 2, max = 40, message = "Incorrect last name")
    private String lastName;

    @NonNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "invalid email")
    private String email;

    @NonNull
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @NonNull
    @Size(min = 4, message = "Your password must be bigger than 4")
    private String password;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(Status.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }


}
