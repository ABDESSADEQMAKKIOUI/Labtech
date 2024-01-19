package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Reactif}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactifDto implements Serializable {
    long id;
    int quantity;
    String nom;
    Date date_expiration;
    String description;
    List<AnalyseReactifDto> analyseReactifs;
    List<EnormDto> enorms;
    long fournisseurId;
    String fournisseurNom;

    /**
     * DTO for {@link com.example.LabTech.entite.AnalyseReactif}
     */
    @Value
    public static class AnalyseReactifDto implements Serializable {
        int id;
        long analyseId;
        String analyseName;
        Status analyseStatus;
        int quantity;
    }

    /**
     * DTO for {@link com.example.LabTech.entite.Enorm}
     */
    @Value
    public static class EnormDto implements Serializable {
        long id;
        String name;
    }
}