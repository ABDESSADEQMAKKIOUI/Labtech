package com.example.LabTech.entite;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Test_analyse> testAnalyses = new ArrayList<>();

    @ManyToOne
    private Reactif reactif;
    private String name ;
    private String unite_mesure ;
    private float plage_normale_min ;
    private float plage_normale_max ;
}
