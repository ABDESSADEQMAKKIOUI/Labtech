package com.example.LabTech.entite;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "reactif", cascade = CascadeType.ALL)
    private List<Enorm> enorms = new ArrayList<>() ;
    @ManyToOne
    private Fournisseur fournisseur;


}

