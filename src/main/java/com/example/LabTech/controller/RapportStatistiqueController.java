package com.example.LabTech.controller;

import com.example.LabTech.entite.RapportStatistique;
import com.example.LabTech.service.RapportStatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapport-statistiques")
public class RapportStatistiqueController {

    @Autowired
    private RapportStatistiqueService rapportStatistiqueService;

    @GetMapping
    public List<RapportStatistique> getAllRapportStatistiques() {
        return rapportStatistiqueService.getAllRapportStatistiques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportStatistique> getRapportStatistiqueById(@PathVariable long id) {
        return rapportStatistiqueService.getRapportStatistiqueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RapportStatistique> addRapportStatistique(@RequestBody RapportStatistique rapportStatistique) {
        RapportStatistique addedRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistique);
        return new ResponseEntity<>(addedRapportStatistique, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RapportStatistique> updateRapportStatistique(@PathVariable long id, @RequestBody RapportStatistique rapportStatistique) {
        if (rapportStatistiqueService.getRapportStatistiqueById(id).isPresent()) {
            rapportStatistique.setId(id);
            RapportStatistique updatedRapportStatistique = rapportStatistiqueService.updateRapportStatistique(rapportStatistique);
            return ResponseEntity.ok(updatedRapportStatistique);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapportStatistique(@PathVariable long id) {
        rapportStatistiqueService.deleteRapportStatistique(id);
        return ResponseEntity.noContent().build();
    }
}

