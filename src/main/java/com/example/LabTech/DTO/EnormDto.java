package com.example.LabTech.DTO;

<<<<<<< HEAD
import com.example.LabTech.entite.enums.Type_Analyse_name;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
=======
>>>>>>> 8a1810513033a2cd4a0c545cf894ac04007763d8
=======
import com.example.LabTech.entite.enums.Status;
import lombok.AllArgsConstructor;
>>>>>>> 05a9764a6c5c576aab1ab3e79e5c4e48de72cc93
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Enorm}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
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
    @Value
    public static class Test_analyseDto implements Serializable {
        long id;
        float resultat;
        String commentaire;
        Status status;
    }
}