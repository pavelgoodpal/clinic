package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.validation.annotation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Doctor", description = "Info about doctor")
public class DoctorDto {

    @Schema(description = "doctor id")
    private Long id;

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

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private WorkWeek workWeek;

    @NotBlank
    private String speciality;

    private List<Order> orders;

    private List<WeekendDay> weekendDays;

    private Cart cart;
}
