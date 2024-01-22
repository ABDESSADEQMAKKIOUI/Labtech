package com.example.LabTech.Exception;

public class AnalyseNotFoundException extends RuntimeException {

    public AnalyseNotFoundException(String message) {
        super(message);
    }

    public AnalyseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
