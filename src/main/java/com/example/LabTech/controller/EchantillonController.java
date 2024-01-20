package com.example.LabTech.controller;

import com.example.LabTech.DTO.EchantillonDto;
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
    public List<EchantillonDto> getAllEchantillons() {
        return echantillonService.getAllEchantillons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EchantillonDto> getEchantillonById(@PathVariable long id) {
        return echantillonService.getEchantillonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EchantillonDto> addEchantillon(@RequestBody EchantillonDto echantillonDto) {
        EchantillonDto addedEchantillon = echantillonService.addEchantillon(echantillonDto);
        return new ResponseEntity<>(addedEchantillon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EchantillonDto> updateEchantillon(@PathVariable long id, @RequestBody EchantillonDto echantillonDto) {
        if (echantillonService.getEchantillonById(id).isPresent()) {
//            echantillonDto.setId(id);
            EchantillonDto updatedEchantillon = echantillonService.updateEchantillon(echantillonDto);
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
