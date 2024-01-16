package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Type_Analyse_name;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Type_Analyse}
 */
@Value
public class Type_AnalyseDto implements Serializable {
    Type_Analyse_name typeAnalyseName;
}