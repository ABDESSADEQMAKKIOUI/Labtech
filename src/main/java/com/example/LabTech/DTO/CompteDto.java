package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Compte}
 */

@Data
@NoArgsConstructor
public class CompteDto implements Serializable {
    String username;
    private String password;
    Role role;
}