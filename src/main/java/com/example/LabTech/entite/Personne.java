package com.example.LabTech.entite;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adress;
    private String email;
    private String tele;
}
