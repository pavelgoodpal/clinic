package com.cshop.cosmeticshop.domain.intity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "treatments")
@NoArgsConstructor
@RequiredArgsConstructor
public class Treatment extends Identifier {

    @NonNull
    private String name;

    @NonNull
    private Long price;

    @NonNull
    private String description;

    @NonNull
    private long treatmentTime;
}
