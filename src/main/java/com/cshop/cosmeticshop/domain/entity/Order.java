package com.cshop.cosmeticshop.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * Order entity
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends Identifier {

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "invalid phone number")
    private String phoneNumber;

    @Email(message = "invalid email")
    private String email;

    private String additionalInfo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startAt;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finishAt;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    @OneToOne
    private Cart cart;

    @ManyToOne
    @JoinTable(name="order_user",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private User user;

    @PrePersist
    private void prePersist() {
        this.creationTime = LocalDateTime.now();
    }


}
