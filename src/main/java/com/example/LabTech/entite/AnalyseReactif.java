package com.example.LabTech.entite;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AnalyseReactif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne
    Analyse analyse ;
    @ManyToOne
    Reactif reactif ;
    private int quantity ;
}
