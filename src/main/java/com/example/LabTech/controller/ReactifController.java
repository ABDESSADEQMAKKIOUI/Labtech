package com.example.LabTech.controller;

import com.example.LabTech.entite.Reactif;
import com.example.LabTech.service.ReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactifs")
public class ReactifController {

    @Autowired
    private ReactifService reactifService;

    @GetMapping
    public List<Reactif> getAllReactifs() {
        return reactifService.getAllReactifs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reactif> getReactifById(@PathVariable long id) {
        return reactifService.getReactifById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reactif> addReactif(@RequestBody Reactif reactif) {
        Reactif addedReactif = reactifService.addReactif(reactif);
        return new ResponseEntity<>(addedReactif, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reactif> updateReactif(@PathVariable long id, @RequestBody Reactif reactif) {
        if (reactifService.getReactifById(id).isPresent()) {
            reactif.setId(id);
            Reactif updatedReactif = reactifService.updateReactif(reactif);
            return ResponseEntity.ok(updatedReactif);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReactif(@PathVariable long id) {
        reactifService.deleteReactif(id);
        return ResponseEntity.noContent().build();
    }
}

