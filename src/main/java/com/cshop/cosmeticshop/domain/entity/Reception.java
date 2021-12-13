package com.cshop.cosmeticshop.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Reception entity
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "receptions")
@NoArgsConstructor
public class Reception extends BaseEntity {

    private Calendar startAt;

    private Calendar finishAt;

}
