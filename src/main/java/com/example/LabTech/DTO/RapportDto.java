package com.example.LabTech.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportDto implements Serializable {
    private String nomAnalyse;
    private String nom;
    private String prenom;
    private String numero;
    private String adresse;
    private String datenaissancepatient;
    private String nomTypeAnalyse;
    private String resultat;
    private String libelleNorme;
    private Double maxValueNorme;
    private Double minValueNorme;
    private String uniteNorme;

    public RapportDto(Object[] result) {
        this.nomAnalyse = (String) result[0];
        this.nom = (String) result[1];
        this.prenom = (String) result[2];
        this.numero = (String) result[3];
        this.adresse = (String) result[4];
        this.datenaissancepatient = (String) result[5];
        this.nomTypeAnalyse = (String) result[6];
        this.resultat = (String) result[7];
        this.libelleNorme = (String) result[8];
        this.maxValueNorme = (Double) result[9];
        this.minValueNorme = (Double) result[10];
        this.uniteNorme = (String) result[11];
    }
}

