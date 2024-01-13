package com.example.LabTech.controller;

import com.example.LabTech.entite.Materiel;
import com.example.LabTech.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materiels")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @GetMapping
    public List<Materiel> getAllMateriels() {
        return materielService.getAllMateriel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materiel> getMaterielById(@PathVariable long id) {
        Optional<Materiel> materiel = materielService.getMaterielById(id);
        return materiel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Materiel> addMateriel(@RequestBody Materiel materiel) {
        Materiel newMateriel = materielService.addMateriel(materiel);
        return new ResponseEntity<>(newMateriel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materiel> updateMateriel(@PathVariable long id, @RequestBody Materiel materiel) {
        if (!materielService.getMaterielById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Materiel updatedMateriel = materielService.updateMateriel(materiel);
        return new ResponseEntity<>(updatedMateriel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriel(@PathVariable long id) {
        if (!materielService.getMaterielById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        materielService.deleteMateriel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
