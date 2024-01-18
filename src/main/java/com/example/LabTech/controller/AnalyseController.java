package com.example.LabTech.controller;


import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.service.AnalyseService;
import com.example.LabTech.service.PlanificationService;
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
    private PlanificationService planificationService ;

    @GetMapping
    public List<AnalyseDto> getAllAnalyses() {
        return analyseService.getAllAnalyses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyseDto> getAnalyseById(@PathVariable long id) {
        return analyseService.getAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnalyseDto> addAnalyse(@RequestBody AnalyseDto analyse) {
        AnalyseDto addedAnalyse = analyseService.addAnalyse(analyse);
        return new ResponseEntity<>(addedAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalyseDto> updateAnalyse(@PathVariable long id, @RequestBody AnalyseDto analyse) {
        if (analyseService.getAnalyseById(id).isPresent()) {
            analyse.setId(id);
            AnalyseDto updatedAnalyse = analyseService.updateAnalyse(analyse ,id);
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
