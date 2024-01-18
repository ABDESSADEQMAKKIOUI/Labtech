package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Analyse}
 */
@NoArgsConstructor
@Data
public class AnalyseDto implements Serializable {
    long id;
    long typeAnalyseId;
    Type_Analyse_name typeAnalyseTypeAnalyseName;
    List<Test_analyseDto> testAnalyses;
    Status status;

    /**
     * DTO for {@link com.example.LabTech.entite.Test_analyse}
     */
    @Value
    public static class Test_analyseDto implements Serializable {
        long id;
        long enormId;
        String enormName;
        String enormUnite_mesure;
        float enormPlage_normale_min;
        float enormPlage_normale_max;
        float resultat;
        String commentaire;
        Status status;
    }
}