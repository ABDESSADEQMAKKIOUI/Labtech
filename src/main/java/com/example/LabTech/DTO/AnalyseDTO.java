package com.example.LabTech.DTO;

import com.example.LabTech.DTO.enums.StatusDTO;
import com.example.LabTech.DTO.enums.Status_AnalyseDTO;
import com.example.LabTech.DTO.enums.Type_AnalyseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class AnalyseDTO {
    private EchantillonDTO echantillon;
    private Type_AnalyseDTO typeAnalyse;
    private Date date_debut;
    private Date date_fin;
    private Status_AnalyseDTO statusAnalyse;
    private Compte responsable;
    private StatusDTO status;
}
