package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.DTO.TestAnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.entite.Test_analyse;

import java.util.Date;

public interface IPlanificationService {

    void affecterTechnicien(TechnitienDto technitien, TestAnalyseDto test_analyse);

    void ChangerDateAnalyse(AnalyseDto analyse, Date dateDebut, Date dateFin);

    void ChangerTechnicien(TechnitienDto technitien, TestAnalyseDto test_analyse);

    void ChangerResponsable(ResponsableDto responsable, AnalyseDto analyse);

    void AjouterTest(AnalyseDto analyse, TestAnalyseDto test_analyse);
}