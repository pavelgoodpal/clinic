package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.Reception;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class ReceptionDTO {

    @NotNull
    private Calendar startAt;
    @NotNull
    private Calendar finishAt;

    public Reception toReception() {
        return new Reception(startAt, finishAt);
    }
}
