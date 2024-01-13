package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Enumerated(EnumType.STRING)
    private Status status ;


}
