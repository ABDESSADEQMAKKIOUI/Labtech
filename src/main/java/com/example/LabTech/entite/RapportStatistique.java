package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Periode_Rapport;
import com.example.LabTech.entite.enums.Type_Rapport;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@Data
public class RapportStatistique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Type_Rapport typeRapport;

    @Enumerated(EnumType.STRING)
    private Periode_Rapport periodeRapport;
    private String description ;

}
