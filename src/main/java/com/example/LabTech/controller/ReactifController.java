package com.example.LabTech.controller;

import com.example.LabTech.DTO.ReactifDto;
import com.example.LabTech.entite.Reactif;
import com.example.LabTech.service.ReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reactifs")
public class ReactifController {

    @Autowired
    private ReactifService reactifService;

    @GetMapping
    public List<ReactifDto> getAllReactifs() {
       return reactifService.getAllReactifs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactifDto> getReactifById(@PathVariable long id) {
        return reactifService.getReactifById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReactifDto> addReactif(@RequestBody ReactifDto reactifDto) {
        ReactifDto addedReactif = reactifService.addReactif(reactifDto);
        return new ResponseEntity<>(addedReactif, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReactifDto> updateReactif(@PathVariable long id, @RequestBody ReactifDto reactifDto) {
        if (!reactifService.getReactifById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reactifDto.setId(id);
        ReactifDto updatedReactif = reactifService.updateReactif(reactifDto);
        return ResponseEntity.ok(updatedReactif);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReactif(@PathVariable long id) {
        if (!reactifService.getReactifById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reactifService.deleteReactif(id);
        return ResponseEntity.noContent().build();
    }
}
