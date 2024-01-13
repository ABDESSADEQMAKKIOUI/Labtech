package com.example.LabTech.controller;

import com.example.LabTech.entite.Technitien;
import com.example.LabTech.service.TechnitientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/technitiens")
public class TechnitienController {

    @Autowired
    private TechnitientService technitienService;

    @GetMapping
    public List<Technitien> getAllTechnitiens() {
        return technitienService.getAlltechnitiens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technitien> getTechnitienById(@PathVariable long id) {
        Optional<Technitien> technitien = technitienService.gettechnitienById(id);
        return technitien.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Technitien> addTechnitien(@RequestBody Technitien technitien) {
        Technitien newTechnitien = technitienService.addtechnitien(technitien);
        return new ResponseEntity<>(newTechnitien, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technitien> updateTechnitien(@PathVariable long id, @RequestBody Technitien technitien) {
        if (!technitienService.gettechnitienById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Technitien updatedTechnitien = technitienService.updatetechnitien(technitien);
        return new ResponseEntity<>(updatedTechnitien, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnitien(@PathVariable long id) {
        if (!technitienService.gettechnitienById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        technitienService.deletetechnitien(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

