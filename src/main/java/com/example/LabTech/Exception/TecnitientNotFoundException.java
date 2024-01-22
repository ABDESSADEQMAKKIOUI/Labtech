package com.example.LabTech.Exception;

public class TecnitientNotFoundException extends  RuntimeException {
    public TecnitientNotFoundException(String message) {
        super(message);
    }

    public TecnitientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
