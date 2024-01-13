package com.example.LabTech.controller;

import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.service.TypeAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-analyses")
public class TypeAnalyseController {

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @GetMapping
    public List<Type_Analyse> getAllTypeAnalyses() {
        return typeAnalyseService.getAllttypeAnalyse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type_Analyse> getTypeAnalyseById(@PathVariable long id) {
        Optional<Type_Analyse> typeAnalyse = typeAnalyseService.gettypeAnalyseById(id);
        return typeAnalyse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Type_Analyse> addTypeAnalyse(@RequestBody Type_Analyse typeAnalyse) {
        Type_Analyse newTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyse);
        return new ResponseEntity<>(newTypeAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type_Analyse> updateTypeAnalyse(@PathVariable long id, @RequestBody Type_Analyse typeAnalyse) {
        if (!typeAnalyseService.gettypeAnalyseById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Type_Analyse updatedTypeAnalyse = typeAnalyseService.updatetypeAnalyse(typeAnalyse);
        return new ResponseEntity<>(updatedTypeAnalyse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAnalyse(@PathVariable long id) {
        if (!typeAnalyseService.gettypeAnalyseById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeAnalyseService.deletetypeAnalyse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
