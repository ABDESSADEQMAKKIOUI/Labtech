package com.example.LabTech.entite;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Reactif> reactifs = new ArrayList<>() ;


}
