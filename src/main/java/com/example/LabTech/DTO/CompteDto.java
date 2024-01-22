package com.example.LabTech.DTO;

import com.example.LabTech.entite.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.LabTech.entite.Compte}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto implements Serializable {
    long id;
    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide.")
    private String username;

    @NotBlank(message = "Le mot de passe ne peut pas être vide.")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères.")
    private String password;

    @NotNull(message = "Le rôle ne peut pas être nul.")
    private Role role;
}