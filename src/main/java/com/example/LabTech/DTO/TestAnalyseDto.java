package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Test_analyse}
 */
@NoArgsConstructor
@Data
public class TestAnalyseDto implements Serializable {
    private long id;

    long analyseId;
    Date analyseDate_debut;
    Date analyseDate_fin;
    Status_Analyse analyseStatusAnalyse;
    Status analyseStatus;
    float resultat;
    Status status;
}