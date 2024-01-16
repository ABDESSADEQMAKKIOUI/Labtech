package com.example.LabTech.DTO;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Responsable}
 */
@Value
public class ResponsableDto implements Serializable {
    String nom;
    String prenom;
    String email;
}