package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.EnumType;
import java.time.LocalDateTime;

/**
 * OutBox entity
 * @author Pave1Pal
 */
@Entity
@Getter
@Setter
@Table(name = "outbox")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OutBox extends BaseEntity {


    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    private String payload;

    private String destination;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;
}
