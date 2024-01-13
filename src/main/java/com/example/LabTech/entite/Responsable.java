package com.example.LabTech.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Responsable extends Personne{
    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL)
    private List<Analyse> analyses ;
    @OneToOne
    private Compte compte ;
}
