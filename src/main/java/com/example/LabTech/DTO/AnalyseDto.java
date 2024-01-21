package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Analyse}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseDto implements Serializable {
    long id;
    long echantillonId;
    Long echantillonPatientId;
    String echantillonPatientNom;
    Date echantillonDate_prend;
    String name;
    Date date_debut;
    Date date_fin;
    Status_Analyse statusAnalyse;
    Long responsableId;
    String responsableNom;
    List<AnalyseReactifDto> analyseReactifs;

    List<Type_AnalyseDto> typeAnalyses;
    Status status;

    /**
     * DTO for {@link com.example.LabTech.entite.AnalyseReactif}
     */
    @Data
    public static class AnalyseReactifDto implements Serializable {
        int id;
        String reactifNom;
        int quantity;
    }

    /**
     * DTO for {@link com.example.LabTech.entite.Type_Analyse}
     */
    @Data
    public static class Type_AnalyseDto implements Serializable {
        long id;
        String name;
    }
}