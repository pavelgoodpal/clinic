package com.cshop.cosmeticshop.exception;

import com.cshop.cosmeticshop.domain.entity.WorkDay;

public class WorkDayNotFoundException extends RuntimeException {

    public WorkDayNotFoundException(String message) {
        super(message);
    }
}
