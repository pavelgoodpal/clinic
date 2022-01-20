package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "admins")
@NoArgsConstructor
@DiscriminatorValue("1")
public class Admin extends User {

    @OneToMany(mappedBy = "admin",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Doctor> doctors;
}
