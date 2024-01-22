package com.example.LabTech.Exception;

public class ReactifNotFoundException extends RuntimeException{
    public ReactifNotFoundException(String message) {
     super(message);
    }

    public ReactifNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
