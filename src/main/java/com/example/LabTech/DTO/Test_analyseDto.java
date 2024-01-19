package com.example.LabTech.DTO;

import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Test_analyse}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test_analyseDto implements Serializable {
    long id;
    long enormId;
    String enormName;
    String enormUnite_mesure;
    float enormPlage_normale_min;
    float enormPlage_normale_max;
    Long technitienId;
    String technitienNom;
    long typeAnalyseId;
    String typeAnalyseName;
    float resultat;
    String commentaire;
    Status status;
}