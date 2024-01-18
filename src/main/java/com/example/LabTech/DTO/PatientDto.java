package com.example.LabTech.DTO;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
=======
>>>>>>> 8a1810513033a2cd4a0c545cf894ac04007763d8
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