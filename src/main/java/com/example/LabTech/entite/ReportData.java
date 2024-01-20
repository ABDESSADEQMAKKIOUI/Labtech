package com.example.LabTech.entite;

import com.example.LabTech.entite.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportData {

    private String analyseName;
    private String typeAnalyseName;
    private String testName;
    private Double plageNormaleMin;
    private Double plageNormaleMax;
    private String resultat;
    private String uniteMesure;
    private String status;


    // Getters and setters
}

