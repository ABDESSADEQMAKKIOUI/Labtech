package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name="analyses")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Echantillon echantillon;

    @ManyToOne
    private Type_Analyse typeAnalyse ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_debut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_fin;

    @Enumerated(EnumType.STRING)
    private Status_Analyse statusAnalyse;

    @ManyToOne
    private Responsable responsable;
    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL)
    private List<Test_analyse> testAnalyses = new ArrayList<>() ;
    @Enumerated(EnumType.STRING)
    private Status status ;


}
