package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long totalPrice;
    @ManyToMany
    @JoinTable(name="cart_service",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="service_id"))
    private List<Treatment> treatments = new ArrayList<>();

    public void calculatePrice() {
        totalPrice = 0L;
        treatments.forEach(treatment -> totalPrice+= treatment.getPrice());
    }

    public void clearServices() {
        treatments.clear();
    }
}
