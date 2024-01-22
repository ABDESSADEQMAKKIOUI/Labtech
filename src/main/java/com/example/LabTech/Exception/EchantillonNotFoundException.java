package com.example.LabTech.Exception;

public class EchantillonNotFoundException extends  ReactifNotFoundException{
    public EchantillonNotFoundException(String message) {
        super(message);
    }

    public EchantillonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
