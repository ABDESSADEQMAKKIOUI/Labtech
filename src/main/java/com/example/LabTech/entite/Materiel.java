package com.example.LabTech.entite;

import com.example.LabTech.entite.enums.Materiel_type;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class Materiel {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String nom ;

    @ManyToOne
    private Echantillon echantillon ;

    @Enumerated(EnumType.STRING)
    private Materiel_type materielType ;
}
