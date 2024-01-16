package com.example.LabTech.DTO;

import lombok.Data;

@Data
public class HistoryDTO {

    private long id;
    private PatientDTO patient;
    private TestDTO test;
    private int nombre;
}

