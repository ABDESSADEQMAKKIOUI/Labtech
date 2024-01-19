package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Materiel_type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Materiel}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterielDto implements Serializable {
    long id;
    String nom;
    long echantillonId;
    Long echantillonPatientId;
    String echantillonPatientNom;
    Date echantillonDate_prend;
    Materiel_type materielType;
}