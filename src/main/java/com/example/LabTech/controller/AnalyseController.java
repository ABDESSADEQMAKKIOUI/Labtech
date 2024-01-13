package com.example.LabTech.controller;


import com.example.LabTech.entite.Analyse;
import com.example.LabTech.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @GetMapping
    public List<Analyse> getAllAnalyses() {
        return analyseService.getAllAnalyses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable long id) {
        return analyseService.getAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Analyse> addAnalyse(@RequestBody Analyse analyse) {
        Analyse addedAnalyse = analyseService.addAnalyse(analyse);
        return new ResponseEntity<>(addedAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyse> updateAnalyse(@PathVariable long id, @RequestBody Analyse analyse) {
        if (analyseService.getAnalyseById(id).isPresent()) {
            analyse.setId(id);
            Analyse updatedAnalyse = analyseService.updateAnalyse(analyse);
            return ResponseEntity.ok(updatedAnalyse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable long id) {
        analyseService.deleteAnalyse(id);
        return ResponseEntity.noContent().build();
    }
}
