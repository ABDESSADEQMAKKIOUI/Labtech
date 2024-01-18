package com.example.LabTech.controller;

import com.example.LabTech.DTO.MaterielDto;
import com.example.LabTech.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiels")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @GetMapping
    public List<MaterielDto> getAllMateriels() {
        return materielService.getAllMateriel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterielDto> getMaterielById(@PathVariable long id) {
        return materielService.getMaterielById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MaterielDto> addMateriel(@RequestBody MaterielDto materielDto) {
        MaterielDto addedMateriel = materielService.addMateriel(materielDto);
        return new ResponseEntity<>(addedMateriel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterielDto> updateMateriel(@PathVariable long id, @RequestBody MaterielDto materielDto) {
        if (materielService.getMaterielById(id).isPresent()) {
            materielDto.setId(id);
            MaterielDto updatedMateriel = materielService.updateMateriel(materielDto);
            return ResponseEntity.ok(updatedMateriel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriel(@PathVariable long id) {
        materielService.deleteMateriel(id);
        return ResponseEntity.noContent().build();
    }
}
