package com.cshop.cosmeticshop.domain.dto;

import lombok.*;

import java.util.Calendar;

/**
 * DTO class for Reception
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceptionDto {

    private Calendar startAt;

    private Calendar finishAt;

}
