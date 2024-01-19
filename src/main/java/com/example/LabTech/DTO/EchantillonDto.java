package com.example.LabTech.DTO;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.entite.Materiel;
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
 * DTO for {@link Echantillon}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonDto implements Serializable {
    long id;
    Long patientId;
    String patientNom;
    String patientPrenom;
    Date date_prend;
    List<MaterielDto> materiels;
    List<AnalyseDto> analyses;

    /**
     * DTO for {@link Materiel}
     */
    @Value
    public static class MaterielDto implements Serializable {
        long id;
        String nom;
    }

    /**
     * DTO for {@link Analyse}
     */
    @Value
    public static class AnalyseDto implements Serializable {
        long id;
        String name;
        Date date_debut;
        Date date_fin;
        Status_Analyse statusAnalyse;
        Status status;
    }
}