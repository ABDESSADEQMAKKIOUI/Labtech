package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Analyse_name;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
=======
>>>>>>> 8a1810513033a2cd4a0c545cf894ac04007763d8
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Enorm}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnormDto implements Serializable {
    long id;
    long typeAnalyseId;
    Type_Analyse_name typeAnalyseTypeAnalyseName;
    String reactifNom;
    String name;
    String unite_mesure;
    float plage_normale_min;
    float plage_normale_max;
}