package com.example.LabTech.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Personne}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonneDto implements Serializable {
    private Long id;

    String nom;
    String prenom;
    String email;
}