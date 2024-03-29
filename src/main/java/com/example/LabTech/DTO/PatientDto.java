package com.example.LabTech.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Patient}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto implements Serializable {
    //long id;
    String nom;
    String prenom;
    String email;


    /**
     * DTO for {@link com.example.LabTech.entite.Echantillon}
     */
    @Value
    public static class EchantillonDto implements Serializable {
        long id;
        Date date_prend;
    }
}