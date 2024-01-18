package com.example.LabTech.controller;

import com.example.LabTech.DTO.TypeAnalyseDto;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.service.TypeAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/type-analyses")
public class TypeAnalyseController {

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @GetMapping
    public List<TypeAnalyseDto> getAllTypeAnalyses() {
      return typeAnalyseService.getAllTypeAnalyses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAnalyseDto> getTypeAnalyseById(@PathVariable long id) {
        return typeAnalyseService.getTypeAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeAnalyseDto> addTypeAnalyse(@RequestBody TypeAnalyseDto typeAnalyseDto) {
        TypeAnalyseDto addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseDto);
        return new ResponseEntity<>(addedTypeAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeAnalyseDto> updateTypeAnalyse(@PathVariable long id, @RequestBody TypeAnalyseDto typeAnalyseDto) {
        if (!typeAnalyseService.gettypeAnalyseById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeAnalyseDto.setId(id);
        TypeAnalyseDto updatedTypeAnalyse = typeAnalyseService.updatetypeAnalyse(typeAnalyseDto);
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
