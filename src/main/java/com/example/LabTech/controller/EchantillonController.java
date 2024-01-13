package com.example.LabTech.controller;

import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.service.EchantillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/echantillons")
public class EchantillonController {

    @Autowired
    private EchantillonService echantillonService;

    @GetMapping
    public List<Echantillon> getAllEchantillons() {
        return echantillonService.getAllEchantillons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Echantillon> getEchantillonById(@PathVariable long id) {
        return echantillonService.getEchantillonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Echantillon> addEchantillon(@RequestBody Echantillon echantillon) {
        Echantillon addedEchantillon = echantillonService.addEchantillon(echantillon);
        return new ResponseEntity<>(addedEchantillon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Echantillon> updateEchantillon(@PathVariable long id, @RequestBody Echantillon echantillon) {
        if (echantillonService.getEchantillonById(id).isPresent()) {
            echantillon.setId(id);
            Echantillon updatedEchantillon = echantillonService.updateEchantillon(echantillon);
            return ResponseEntity.ok(updatedEchantillon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEchantillon(@PathVariable long id) {
        echantillonService.deleteEchantillon(id);
        return ResponseEntity.noContent().build();
    }
}
