package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.Reception;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceptionDto {

    private Calendar startAt;

    private Calendar finishAt;

}
