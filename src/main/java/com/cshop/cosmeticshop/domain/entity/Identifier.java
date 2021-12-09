package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Identifier {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Override
    public boolean equals(Object o) {
        Identifier identifier = (Identifier) o;
        return this.getId().equals(identifier.getId());
    }


}
