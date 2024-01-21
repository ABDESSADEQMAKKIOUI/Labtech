package com.example.LabTech.controller;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping
    public List<CompteDto> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDto> getCompteById(@PathVariable long id) {
        return compteService.getCompteById(id)
                .map(compte -> new ResponseEntity<>(compte, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CompteDto> addCompte(@RequestBody CompteDto compteDto) {
        CompteDto newCompte = compteService.addCompte(compteDto);
        return new ResponseEntity<>(newCompte, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteDto> updateCompte(@PathVariable long id, @RequestBody CompteDto compteDto) {
        if (!compteService.getCompteById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CompteDto updatedCompte = compteService.updateCompte(compteDto);
        return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable long id) {
        if (!compteService.getCompteById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        compteService.deleteCompte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
