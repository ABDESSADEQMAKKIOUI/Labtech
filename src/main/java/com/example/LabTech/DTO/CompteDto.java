package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Compte}
 */
@Value
public class CompteDto implements Serializable {
    String username;
    Role role;
}