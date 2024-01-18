package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Materiel_type;
import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Materiel}
 */
@NoArgsConstructor
@Data
public class MaterielDto implements Serializable {
    private long id;

    String nom;
    long typeAnalyseId;
    Type_Analyse_name typeAnalyseTypeAnalyseName;
    Materiel_type materielType;
}