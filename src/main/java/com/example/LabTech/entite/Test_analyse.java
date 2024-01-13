package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Status;
import javax.persistence.*;
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
    @JoinColumn(name = "name", referencedColumnName = "name")
    private Enorm enorm;

    @ManyToOne
    private Technitien technitien;

    private float resultat;
    private String commentaire;

    @Enumerated(EnumType.STRING)
    private Status status;

}

