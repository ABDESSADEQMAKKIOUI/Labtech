package com.example.LabTech.controller;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.service.TechnitientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/technitiens")
public class TechnitienController {

    @Autowired
    private TechnitientService technitienService;

    @GetMapping
    public List<TechnitienDto> getAllTechnitiens() {
       return technitienService.getAllTechnitiens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnitienDto> getTechnitienById(@PathVariable long id ,TechnitienDto technitienDto) {
        if (technitienService.getTechnitienById(id).isPresent()) {
            technitienDto.setId(id);
            TechnitienDto updatedEnorm = technitienService.updateTechnitien(technitienDto);
            return ResponseEntity.ok(updatedEnorm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TechnitienDto> addTechnitien(@RequestBody TechnitienDto technitienDto) {
        TechnitienDto newTechnitien = technitienService.addtechnitien(technitienDto);
        return new ResponseEntity<>(newTechnitien, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnitienDto> updateTechnitien(@PathVariable long id, @RequestBody TechnitienDto technitienDto) {
        if (!technitienService.gettechnitienById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        technitienDto.setId(id);
        TechnitienDto updatedTechnitien = technitienService.updatetechnitien(technitienDto);
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
