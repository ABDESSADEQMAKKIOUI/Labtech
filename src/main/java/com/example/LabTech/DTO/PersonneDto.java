package com.example.LabTech.DTO;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Personne}
 */
@Value
public class PersonneDto implements Serializable {
    String nom;
    String prenom;
    String email;
}