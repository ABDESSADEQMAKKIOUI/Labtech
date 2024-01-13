package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Type_Analyse_name;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@Data
public class Type_Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Enumerated(EnumType.STRING)
    private Type_Analyse_name typeAnalyseName ;

    @OneToMany(mappedBy = "typeAnalyse", cascade = CascadeType.ALL)
    private List<Materiel> materiels = new ArrayList<>();

    @OneToMany(mappedBy = "typeAnalyse", cascade = CascadeType.ALL)
    private List<Enorm> enorms = new ArrayList<>();

    @OneToMany(mappedBy = "typeAnalyse", cascade = CascadeType.ALL)
    private List<Analyse> analyses = new ArrayList<>();


}
