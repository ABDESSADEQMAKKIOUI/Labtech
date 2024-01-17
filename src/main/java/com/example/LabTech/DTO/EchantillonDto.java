package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Echantillon}
 */

@Data
@NoArgsConstructor(force = true)
public class EchantillonDto implements Serializable {
    private long id;

    Long patientId;
    String patientNom;
    String patientPrenom;
    String patientAdress;
    String patientEmail;
    String patientTele;
    Date date_prend;
    List<AnalyseDto> analyses;

    /**
     * DTO for {@link com.example.LabTech.entite.Analyse}
     */
    @Value
    public static class AnalyseDto implements Serializable {
        long id;
        Date date_debut;
        Date date_fin;
        Status_Analyse statusAnalyse;
        Status status;
    }
}