package com.example.LabTech.controller;

import com.example.LabTech.entite.*;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.service.RapportStatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rapport-statistique")
public class RapportStatistiqueController {

    @Autowired
    private RapportStatistiqueService rapportStatistiqueService;

    // Endpoints pour RapportStatistique
    @GetMapping
    public List<RapportStatistique> getAllRapportStatistiques() {
        return rapportStatistiqueService.getAllRapportStatistiques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportStatistique> getRapportStatistiqueById(@PathVariable long id) {
        Optional<RapportStatistique> rapportStatistique = rapportStatistiqueService.getRapportStatistiqueById(id);
        return rapportStatistique.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RapportStatistique> addRapportStatistique(@RequestBody RapportStatistique rapportStatistique) {
        RapportStatistique createdRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistique);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdRapportStatistique.getId())
                        .toUri())
                .body(createdRapportStatistique);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RapportStatistique> updateRapportStatistique(@PathVariable long id,
                                                                       @RequestBody RapportStatistique rapportStatistique) {
        rapportStatistique.setId(id);
        RapportStatistique updatedRapportStatistique = rapportStatistiqueService.updateRapportStatistique(rapportStatistique);
        return ResponseEntity.ok(updatedRapportStatistique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapportStatistique(@PathVariable long id) {
        rapportStatistiqueService.deleteRapportStatistique(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints pour Analyse
    @GetMapping("/analysesByStatus/{status}")
    public List<Analyse> getAnalyseByStatus(@PathVariable Status_Analyse status) {
        return rapportStatistiqueService.getAnalyseByStatus(status);
    }

    @GetMapping("/analysesByDateRange")
    public List<Analyse> getAnalysesByDateRange(@RequestParam("startDate") Date startDate,
                                                @RequestParam("endDate") Date endDate) {
        return rapportStatistiqueService.getAnalysesByDateRange(startDate, endDate);
    }

    @GetMapping("/countByStatusAnalyse/{status}")
    public long countByStatusAnalyse(@PathVariable Status_Analyse status) {
        return rapportStatistiqueService.countByStatusAnalyse(status);
    }

    // Endpoints pour Echantillon
    @GetMapping("/echantillonsByPatient")
    public Map<Patient, List<Echantillon>> getEchantillonsByPatient() {
        return rapportStatistiqueService.getEchantillonsByPatient();
    }

    // Endpoints pour Reactif
    @GetMapping("/expiredReactifs")
    public List<Reactif> getExpiredReactifs() {
        return rapportStatistiqueService.getExpiredReactifs();
    }

    @GetMapping("/reactifsByFournisseur")
    public Map<Fournisseur, List<Reactif>> getReactifsByFournisseur() {
        return rapportStatistiqueService.getReactifsByFournisseur();
    }

    // ...

    // Endpoints pour Enorm
    @GetMapping("/enormsByTypeAnalyse")
    public Map<Type_Analyse, List<Enorm>> getEnormsByTypeAnalyse() {
        return rapportStatistiqueService.getEnormsByTypeAnalyse();
    }

    // Endpoints pour Fournisseur
    @GetMapping("/fournisseurWithReactifs")
    public Map<Fournisseur, List<Reactif>> getFournisseurWithReactifs() {
        return rapportStatistiqueService.getFournisseurWithReactifs();
    }

    // Endpoints pour Patient
    @GetMapping("/patientsWithSampleCount")
    public Map<Patient, Long> getPatientsWithSampleCount() {
        return rapportStatistiqueService.getPatientsWithSampleCount();
    }

    // Endpoints pour Responsable
    @GetMapping("/responsableWithAnalyses")
    public Map<Responsable, List<Analyse>> getResponsableWithAnalyses() {
        return rapportStatistiqueService.getResponsableWithAnalyses();
    }

    // Endpoints pour Technitien
    @GetMapping("/technitienWithTests")
    public Map<Technitien, List<Test_analyse>> getTechnitienWithTests() {
        return rapportStatistiqueService.getTechnitienWithTests();
    }

    // Endpoints pour Test_analyse
    @GetMapping("/testsByEnorm")
    public Map<Enorm, List<Test_analyse>> getTestsByEnorm() {
        return rapportStatistiqueService.getTestsByEnorm();
    }

    @GetMapping("/testsByStatus")
    public Map<Status, List<Test_analyse>> getTestsByStatus() {
        return rapportStatistiqueService.getTestsByStatus();
    }

    // Endpoints pour Type_Analyse
    @GetMapping("/typeAnalysesWithMateriels")
    public Map<Type_Analyse, List<Materiel>> getTypeAnalysesWithMateriels() {
        return rapportStatistiqueService.getTypeAnalysesWithMateriels();
    }

    @GetMapping("/typeAnalysesWithEnorms")
    public Map<Type_Analyse, List<Enorm>> getTypeAnalysesWithEnorms() {
        return rapportStatistiqueService.getTypeAnalysesWithEnorms();
    }



}



