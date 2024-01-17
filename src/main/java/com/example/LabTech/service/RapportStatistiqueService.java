package com.example.LabTech.service;

import com.example.LabTech.entite.*;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.repository.*;
import com.example.LabTech.service.interfaces.IRapportStatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RapportStatistiqueService implements IRapportStatistiqueService {

    @Autowired
    private RapportStatistiqueRepository rapportStatistiqueRepository;
    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private EchantillonRepository echantillonRepository;

    @Autowired
    private ReactifRepository reactifRepository;

    @Autowired
    private EnormRepository enormRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ResponsbleRepository responsableRepository;

    @Autowired
    private TechnitienRepository technitienRepository;

    @Autowired
    private TestRepository testAnalyseRepository;

    @Autowired
    private TypeAnalyseRepository typeAnalyseRepository;

    // Méthodes de service pour la logique métier liée à Rapport_Statistique
    @Override
    public List<RapportStatistique> getAllRapportStatistiques() {
        return rapportStatistiqueRepository.findAll();
    }
    @Override
    public Optional<RapportStatistique> getRapportStatistiqueById(long id) {
        return rapportStatistiqueRepository.findById(id);
    }
    @Override
    public RapportStatistique addRapportStatistique(RapportStatistique rapportStatistique) {
        return rapportStatistiqueRepository.save(rapportStatistique);
    }
    @Override
    public RapportStatistique updateRapportStatistique(RapportStatistique rapportStatistique) {
        return rapportStatistiqueRepository.save(rapportStatistique);
    }
    @Override
    public void deleteRapportStatistique(long id) {
        rapportStatistiqueRepository.deleteById(id);
    }
    @Override
    public List<Analyse> getAnalyseByStatus(Status_Analyse statusAnalyse) {
        return analyseRepository.getAnalyseByStatus(statusAnalyse);
    }
    @Override
    // Méthode pour récupérer les analyses dans une plage de dates spécifiée
    public List<Analyse> getAnalysesByDateRange(Date startDate, Date endDate) {
        return analyseRepository.findByDateDebutBetween(startDate, endDate);
    }
    @Override
    public  long countByStatusAnalyse(Status_Analyse statusAnalyse){
        return analyseRepository.countByStatusAnalyse(statusAnalyse);
    }

    // Méthode pour calculer la moyenne des résultats pour chaque type d'analyse
//    public Map<Type_Analyse, Double> getAverageResultByTypeAnalyse() {
//        return analyseRepository.findAverageResultByTypeAnalyse();
//    }

    // Méthode pour récupérer les échantillons associés à chaque patient
    @Override
    public Map<Patient, List<Echantillon>> getEchantillonsByPatient() {
        return echantillonRepository.findAll().stream()
                .collect(Collectors.groupingBy(Echantillon::getPatient));
    }

    // Méthode pour récupérer les échantillons dans une plage de dates spécifiée
    @Override
    public List<Echantillon> getEchantillonsByDateRange(Date startDate, Date endDate) {
        return echantillonRepository.findByDatePrendBetween(startDate, endDate);
    }

    // Méthode pour récupérer les réactifs expirés
    @Override
    public List<Reactif> getExpiredReactifs() {
        return reactifRepository.findByDateExpirationBeforeNow();
    }

    // Méthode pour récupérer les réactifs groupés par fournisseur
    @Override
    public Map<Fournisseur, List<Reactif>> getReactifsByFournisseur() {
        return fournisseurRepository.findAll().stream()
                .collect(Collectors.toMap(
                        fournisseur -> fournisseur,
                        fournisseur -> fournisseur.getReactifs()
                ));
    }

    // Méthode pour récupérer les normes groupées par type d'analyse
    @Override
    public Map<Type_Analyse, List<Enorm>> getEnormsByTypeAnalyse() {
        return enormRepository.findAll().stream()
                .collect(Collectors.groupingBy(Enorm::getTypeAnalyse));
    }

//    // Méthode pour récupérer les normes dans une plage spécifiée
//    public List<Enorm> getEnormsInRange(Date startDate, Date endDate) {
//        return enormRepository.findByDateBetween(startDate, endDate);
//    }

    // Méthode pour récupérer les fournisseurs avec la liste des réactifs associés
    @Override
    public Map<Fournisseur, List<Reactif>> getFournisseurWithReactifs() {
        return fournisseurRepository.findAll().stream()
                .collect(Collectors.toMap(
                        fournisseur -> fournisseur,
                        fournisseur -> fournisseur.getReactifs()
                ));
    }

    // Méthode pour récupérer les patients avec le nombre d'échantillons associés
    @Override
    public Map<Patient, Long> getPatientsWithSampleCount() {
        return patientRepository.findAll().stream()
                .collect(Collectors.toMap(
                        patient -> patient,
                        patient -> (long) patient.getEchantillons().size()
                ));
    }

    // Méthode pour récupérer les responsables avec la liste des analyses associées
    @Override
    public Map<Responsable, List<Analyse>> getResponsableWithAnalyses() {
        return responsableRepository.findAll().stream()
                .collect(Collectors.toMap(
                        responsable -> responsable,
                        responsable -> responsable.getAnalyses()
                ));
    }

    // Méthode pour récupérer les techniciens avec la liste des tests associés
    @Override
    public Map<Technitien, List<Test_analyse>> getTechnitienWithTests() {
        return technitienRepository.findAll().stream()
                .collect(Collectors.toMap(
                        technitien -> technitien,
                        technitien -> technitien.getTestAnalyses()
                ));
    }

    // Méthode pour récupérer les tests groupés par norme
    @Override
    public Map<Enorm, List<Test_analyse>> getTestsByEnorm() {
        return testAnalyseRepository.findAll().stream()
                .collect(Collectors.groupingBy(Test_analyse::getEnorm));
    }

    // Méthode pour récupérer les tests groupés par statut
    @Override
    public Map<Status, List<Test_analyse>> getTestsByStatus() {
        return testAnalyseRepository.findAll().stream()
                .collect(Collectors.groupingBy(Test_analyse::getStatus));
    }

    // Méthode pour récupérer les types d'analyse avec la liste des matériels associés
    @Override
    public Map<Type_Analyse, List<Materiel>> getTypeAnalysesWithMateriels() {
        return typeAnalyseRepository.findAll().stream()
                .collect(Collectors.toMap(
                        typeAnalyse -> typeAnalyse,
                        typeAnalyse -> typeAnalyse.getMateriels()
                ));
    }

    // Méthode pour récupérer les types d'analyse avec la liste des normes associées
    @Override
    public Map<Type_Analyse, List<Enorm>> getTypeAnalysesWithEnorms() {
        return typeAnalyseRepository.findAll().stream()
                .collect(Collectors.toMap(
                        typeAnalyse -> typeAnalyse,
                        typeAnalyse -> typeAnalyse.getEnorms()
                ));
    }

}

