package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Rapport;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.RapportStatistique}
 */
@NoArgsConstructor
@Data
public class RapportStatistiqueDto implements Serializable {
    private long id;

    Type_Rapport typeRapport;
    String description;
}