package com.example.LabTech.entite;

import javax.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@MappedSuperclass
@Data
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide.")
    private String prenom;

    private String adress;

    @Email(message = "L'adresse e-mail doit être valide.")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres.")
    private String tele;

}
