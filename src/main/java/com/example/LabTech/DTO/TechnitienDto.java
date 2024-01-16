package com.example.LabTech.DTO;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Technitien}
 */
@Value
public class TechnitienDto implements Serializable {
    String nom;
    String prenom;
    String email;
}