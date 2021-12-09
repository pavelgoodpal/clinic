package com.cshop.cosmeticshop.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "receptions")
@NoArgsConstructor
public class Reception extends Identifier{

    private Calendar startAt;

    private Calendar finishAt;

}
