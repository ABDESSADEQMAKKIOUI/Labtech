package com.example.LabTech.Exception;

public class ResponsableNoteFoundException extends  RuntimeException{
    public ResponsableNoteFoundException(String message) {
        super(message);
    }

    public ResponsableNoteFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
