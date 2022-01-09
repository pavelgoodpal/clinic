package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class are extended all entities
 * @author Pave1Pal
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final BaseEntity that = (BaseEntity) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
