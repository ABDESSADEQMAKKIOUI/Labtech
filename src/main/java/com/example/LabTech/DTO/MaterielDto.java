package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Materiel_type;
import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Materiel}
 */
@Value
public class MaterielDto implements Serializable {
    String nom;
    long typeAnalyseId;
    Type_Analyse_name typeAnalyseTypeAnalyseName;
    Materiel_type materielType;
}