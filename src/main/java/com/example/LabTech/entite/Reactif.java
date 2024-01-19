package com.example.LabTech.entite;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<AnalyseReactif> analyseReactifs = new ArrayList<>() ;

    @OneToMany(mappedBy = "reactif", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Enorm> enorms = new ArrayList<>() ;

    @ManyToOne
    private Fournisseur fournisseur;


}

