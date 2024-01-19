package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.AnalyseReactif}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseReactifDto implements Serializable {
    int id;
    long analyseId;
    String analyseName;
    Date analyseDate_debut;
    Date analyseDate_fin;
    Status_Analyse analyseStatusAnalyse;
    Status analyseStatus;
    long reactifId;
    int reactifQuantity;
    String reactifNom;
    Date reactifDate_expiration;
    String reactifDescription;
    int quantity;
}