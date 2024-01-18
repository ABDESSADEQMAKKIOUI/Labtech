package com.example.LabTech.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Responsable}
 */
@NoArgsConstructor
@Data
public class ResponsableDto implements Serializable {
    private Long id;
    String nom;
    String prenom;
    String email;
}