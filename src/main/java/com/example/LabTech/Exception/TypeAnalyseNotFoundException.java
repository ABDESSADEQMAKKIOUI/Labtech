package com.example.LabTech.Exception;

public class TypeAnalyseNotFoundException extends RuntimeException{
    public TypeAnalyseNotFoundException(String message) {
        super(message);
    }

    public TypeAnalyseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
