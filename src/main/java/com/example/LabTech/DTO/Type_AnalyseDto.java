package com.example.LabTech.DTO;

import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Type_Analyse}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type_AnalyseDto implements Serializable {
    long id;
    String name;
    List<Test_analyseDto> testAnalyses;
    long analyseId;
    String analyseName;
    Date analyseDate_debut;
    Date analyseDate_fin;
    Status_Analyse analyseStatusAnalyse;
    Status analyseStatus;

    /**
     * DTO for {@link Test_analyse}
     */
    //@Value
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Test_analyseDto implements Serializable {
        long id;
        float resultat;
        String commentaire;
        Status status;
    }
}