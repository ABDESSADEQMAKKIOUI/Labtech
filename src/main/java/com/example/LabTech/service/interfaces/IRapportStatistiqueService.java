package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.*;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRapportStatistiqueService {

    List<RapportStatistique> getAllRapportStatistiques();

    Optional<RapportStatistique> getRapportStatistiqueById(long id);

    RapportStatistique addRapportStatistique(RapportStatistique rapportStatistique);

    RapportStatistique updateRapportStatistique(RapportStatistique rapportStatistique);

    void deleteRapportStatistique(long id);

    List<Analyse> getAnalyseByStatus(Status_Analyse statusAnalyse);

    List<Analyse> getAnalysesByDateRange(Date startDate, Date endDate);

    long countByStatusAnalyse(Status_Analyse statusAnalyse);

    Map<Patient, List<Echantillon>> getEchantillonsByPatient();

    List<Echantillon> getEchantillonsByDateRange(Date startDate, Date endDate);

    List<Reactif> getExpiredReactifs();

    Map<Fournisseur, List<Reactif>> getReactifsByFournisseur();

    Map<Type_Analyse, List<Enorm>> getEnormsByTypeAnalyse();

    Map<Fournisseur, List<Reactif>> getFournisseurWithReactifs();

    Map<Patient, Long> getPatientsWithSampleCount();

    Map<Responsable, List<Analyse>> getResponsableWithAnalyses();

    Map<Technitien, List<Test_analyse>> getTechnitienWithTests();

    Map<Enorm, List<Test_analyse>> getTestsByEnorm();

    Map<Status, List<Test_analyse>> getTestsByStatus();

    Map<Type_Analyse, List<Materiel>> getTypeAnalysesWithMateriels();

    Map<Type_Analyse, List<Enorm>> getTypeAnalysesWithEnorms();
}