package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @NotBlank(message = "Le champ 'name' ne peut pas être vide.")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_debut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_fin;

    @Enumerated(EnumType.STRING)
    private Status_Analyse statusAnalyse;

    @ManyToOne
    private Responsable responsable;

    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<AnalyseReactif> analyseReactifs = new ArrayList<>() ;

    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Type_Analyse> typeAnalyses = new ArrayList<>() ;

    @Enumerated(EnumType.STRING)
    private Status status ;


}
