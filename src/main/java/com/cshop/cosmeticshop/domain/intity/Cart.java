package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long totalPrice;
    @ManyToMany
    @JoinTable(name="treatment_cart",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="treatment_id"))
    private List<Treatment> treatments = new ArrayList<>();

}
