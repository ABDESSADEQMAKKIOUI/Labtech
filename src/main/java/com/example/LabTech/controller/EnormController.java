package com.example.LabTech.controller;

import com.example.LabTech.entite.Enorm;
import com.example.LabTech.service.EnormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enorms")
public class EnormController {

    @Autowired
    private EnormService enormService;

    @GetMapping
    public List<Enorm> getAllEnorms() {
        return enormService.getAllEnorms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enorm> getEnormById(@PathVariable long id) {
        Optional<Enorm> enorm = enormService.getEnormsById(id);
        return enorm.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Enorm> addEnorm(@RequestBody Enorm enorm) {
        Enorm newEnorm = enormService.addEnorms(enorm);
        return new ResponseEntity<>(newEnorm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enorm> updateEnorm(@PathVariable long id, @RequestBody Enorm enorm) {
        if (!enormService.getEnormsById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Enorm updatedEnorm = enormService.updateEnorms(enorm);
        return new ResponseEntity<>(updatedEnorm, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnorm(@PathVariable long id) {
        if (!enormService.getEnormsById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        enormService.deleteEnorms(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
