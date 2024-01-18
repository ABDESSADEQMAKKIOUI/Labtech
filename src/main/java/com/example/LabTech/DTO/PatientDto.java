package com.example.LabTech.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Patient}
 */
@NoArgsConstructor
@Data
public class PatientDto implements Serializable {
    private Long id;
    String nom;
    String prenom;
    String email;
    Collection<EchantillonDto> echantillons;

    /**
     * DTO for {@link com.example.LabTech.entite.Echantillon}
     */
    @Value
    public static class EchantillonDto implements Serializable {
        long id;
        Date date_prend;
    }
}