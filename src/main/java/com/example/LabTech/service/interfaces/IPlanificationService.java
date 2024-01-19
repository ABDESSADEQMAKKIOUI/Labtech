package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.DTO.Test_analyseDto;

import java.util.Date;

public interface IPlanificationService {

    void affecterTechnicien(TechnitienDto technitien, Test_analyseDto test_analyse);

    void ChangerDateAnalyse(AnalyseDto analyse, Date dateDebut, Date dateFin);

    void ChangerTechnicien(TechnitienDto technitien, Test_analyseDto test_analyse);

    void ChangerResponsable(ResponsableDto responsable, AnalyseDto analyse);

    void AjouterTest(AnalyseDto analyse, Test_analyseDto test_analyse);
}