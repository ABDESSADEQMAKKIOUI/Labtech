package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Test_analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Type_Analyse typeAnalyse;

    private String name ;
    @OneToOne(mappedBy = "nom_testAnalyse" , cascade = CascadeType.ALL)
    @JoinColumn(name = "name", referencedColumnName = "name")
    private Enorm enorm;

    @ManyToOne
    private Technitien technitien;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_debut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_fin;

    private float resultat;
    private String commentaire;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Reactif reactif;


}

