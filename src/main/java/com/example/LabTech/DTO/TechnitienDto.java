package com.example.LabTech.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Technitien}
 */
@NoArgsConstructor
@Data
public class TechnitienDto implements Serializable {
    private Long id;
    String nom;
    String prenom;
    String email;
}