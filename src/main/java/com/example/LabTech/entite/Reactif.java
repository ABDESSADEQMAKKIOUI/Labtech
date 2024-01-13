package com.example.LabTech.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Reactif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date date_expiration;

    private String description;

    @ManyToOne
    private Fournisseur fournisseur;


}

