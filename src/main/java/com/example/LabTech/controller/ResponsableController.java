package com.example.LabTech.controller;

import com.example.LabTech.entite.Responsable;
import com.example.LabTech.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @GetMapping
    public List<Responsable> getAllResponsables() {
        return responsableService.getAllresponsable();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsable> getResponsableById(@PathVariable long id) {
        Optional<Responsable> responsable = responsableService.getResponsableById(id);
        return responsable.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Responsable> addResponsable(@RequestBody Responsable responsable) {
        Responsable newResponsable = responsableService.addResponsable(responsable);
        return new ResponseEntity<>(newResponsable, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsable> updateResponsable(@PathVariable long id, @RequestBody Responsable responsable) {
        if (!responsableService.getResponsableById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Responsable updatedResponsable = responsableService.updateResponsable(responsable);
        return new ResponseEntity<>(updatedResponsable, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsable(@PathVariable long id) {
        if (!responsableService.getResponsableById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        responsableService.deleteResponsable(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
