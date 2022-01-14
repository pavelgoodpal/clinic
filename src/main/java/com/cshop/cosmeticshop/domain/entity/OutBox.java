package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
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
public class OutBox extends BaseEntity{


    private String eventType;

    private String payload;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;
}
