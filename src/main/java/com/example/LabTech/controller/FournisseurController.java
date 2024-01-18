package com.example.LabTech.controller;

import com.example.LabTech.DTO.FournisseurDto;
import com.example.LabTech.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<FournisseurDto> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable long id) {
        return fournisseurService.getFournisseurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FournisseurDto> addFournisseur(@RequestBody FournisseurDto fournisseurDto) {
        FournisseurDto addedFournisseur = fournisseurService.addFournisseur(fournisseurDto);
        return new ResponseEntity<>(addedFournisseur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurDto> updateFournisseur(@PathVariable long id, @RequestBody FournisseurDto fournisseurDto) {
        if (fournisseurService.getFournisseurById(id).isPresent()) {
            fournisseurDto.setId(id);
            FournisseurDto updatedFournisseur = fournisseurService.updateFournisseur(fournisseurDto);
            return ResponseEntity.ok(updatedFournisseur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }
}
