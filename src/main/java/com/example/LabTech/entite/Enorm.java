package com.example.LabTech.entite;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class Enorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToMany
    @JoinColumn(name = "name", referencedColumnName = "name")
    private List<Test_analyse> testAnalyses = new ArrayList<>();
    @ManyToOne
    private Type_Analyse typeAnalyse;
    @ManyToOne
    private Reactif reactif;
    private String name ;
    private String unite_mesure ;
    private float plage_normale_min ;
    private float plage_normale_max ;
}
