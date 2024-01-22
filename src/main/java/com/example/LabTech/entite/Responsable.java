package com.example.LabTech.entite;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Responsable extends Personne{
    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Analyse> analyses ;
    @OneToOne
    private Compte compte ;
}
