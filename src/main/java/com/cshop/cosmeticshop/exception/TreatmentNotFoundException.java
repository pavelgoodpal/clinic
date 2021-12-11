package com.cshop.cosmeticshop.exception;
/**
 * Exception is thrown when treatment not found in repository
 * @author Pave1Pal
 */
public class TreatmentNotFoundException extends Exception{

    public TreatmentNotFoundException(String string) {
        super(string);
    }
}
