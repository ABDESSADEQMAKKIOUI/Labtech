package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.entite.Test_analyse;

import java.util.Date;

public interface IPlanificationService {

    void affecterTechnicien(Technitien technitien, Test_analyse test_analyse);

    void ChangerDateAnalyse(Analyse analyse, Date dateDebut, Date dateFin);

    void ChangerTechnicien(Technitien technitien, Test_analyse test_analyse);

    void ChangerResponsable(Responsable responsable, Analyse analyse);

    void AjouterTest(Analyse analyse, Test_analyse test_analyse);
}