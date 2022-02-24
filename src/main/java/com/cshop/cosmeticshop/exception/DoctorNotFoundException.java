package com.cshop.cosmeticshop.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String str) {
        super(str);
    }
}
