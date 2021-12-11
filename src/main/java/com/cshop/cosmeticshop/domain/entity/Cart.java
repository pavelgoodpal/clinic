package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart entity
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "carts")
@NoArgsConstructor
public class Cart extends Identifier{


    private Long totalPrice;
    @ManyToMany
    @JoinTable(name="treatment_cart",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="treatment_id"))
    private List<Treatment> treatments = new ArrayList<>();

}
