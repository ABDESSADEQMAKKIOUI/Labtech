package com.example.LabTech.Exception;

public class TestAnalyseNotFoundException extends RuntimeException{
    public TestAnalyseNotFoundException(String message) {
        super(message);
    }

    public TestAnalyseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
