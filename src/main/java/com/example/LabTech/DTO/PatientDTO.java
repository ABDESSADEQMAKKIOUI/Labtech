package com.example.LabTech.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PatientDTO extends Personne {

    private long id;
    private List<HistoryDTO> histories;
}
