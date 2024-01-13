package com.example.LabTech.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@Data
public class Enorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private Test_analyse nom_testAnalyse;
    private String unite_mesure ;
    private float plage_normale_min ;
    private float plage_normale_max ;
}
