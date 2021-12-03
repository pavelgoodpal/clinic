package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity(name="receptions")
@NoArgsConstructor
public class Reception {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Calendar startAt;

    private Calendar finishAt;

    public Reception(Calendar startAt, Calendar finishAt) {
        this.startAt = startAt;
        this.finishAt = finishAt;
    }
}
