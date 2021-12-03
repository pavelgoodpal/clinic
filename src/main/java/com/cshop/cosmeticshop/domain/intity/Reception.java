package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity(name="receptions")
public class Reception {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Calendar startAt;

    private Calendar finishAt;

    public Reception() {}
}
