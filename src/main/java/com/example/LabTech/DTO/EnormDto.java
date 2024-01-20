package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.AllArgsConstructor;
import com.example.LabTech.entite.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Enorm}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnormDto implements Serializable {
    long id;
    List<Test_analyseDto> testAnalyses;
    long reactifId;
    String reactifNom;
    String name;
    String unite_mesure;
    float plage_normale_min;
    float plage_normale_max;

    /**
     * DTO for {@link com.example.LabTech.entite.Test_analyse}
     */

    public static class Test_analyseDto implements Serializable {
        long id;
        float resultat;
        String commentaire;
        Status status;
    }
}