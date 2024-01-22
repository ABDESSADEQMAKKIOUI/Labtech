package com.example.LabTech.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnalyseNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(AnalyseNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReactifNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ReactifNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(PatientNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TecnitientNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(TecnitientNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TestAnalyseNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(TestAnalyseNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TypeAnalyseNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(TypeAnalyseNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EchantillonNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(EchantillonNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

