package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Type_Analyse}
 */
@NoArgsConstructor
@Data
public class TypeAnalyseDto implements Serializable {
    private long id;

    Type_Analyse_name typeAnalyseName;
}