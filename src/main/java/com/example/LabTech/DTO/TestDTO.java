package com.example.LabTech.DTO;

import com.example.LabTech.DTO.enums.StatusDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TestDTO {

    private long id;
    private AnalyseDTO analyse;
    private Compte technitien;
    private Date date_debut;
    private Date date_fin;
    private float resultat;
    private String commentaire;
    private StatusDTO status;
    private ReactifDTO reactif;
    private List<HistoryDTO> histories;
}
