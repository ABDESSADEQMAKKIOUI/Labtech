package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Rapport;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.RapportStatistique}
 */
@Value
public class RapportStatistiqueDto implements Serializable {
    Type_Rapport typeRapport;
    String description;
}