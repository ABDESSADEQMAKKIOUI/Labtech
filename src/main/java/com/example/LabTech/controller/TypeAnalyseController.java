package com.example.LabTech.controller;

import com.example.LabTech.DTO.Type_AnalyseDto;
import com.example.LabTech.service.TypeAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-analyses")
public class TypeAnalyseController {

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @GetMapping
    public List<Type_AnalyseDto> getAllTypeAnalyses() {
      return typeAnalyseService.getAllttypeAnalyse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type_AnalyseDto> getTypeAnalyseById(@PathVariable long id) {
        return typeAnalyseService.gettypeAnalyseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Type_AnalyseDto> addTypeAnalyse(@RequestBody Type_AnalyseDto typeAnalyseDto) {
        Type_AnalyseDto addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseDto);
        return new ResponseEntity<>(addedTypeAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type_AnalyseDto> updateTypeAnalyse(@PathVariable long id, @RequestBody Type_AnalyseDto typeAnalyseDto) {
        if (!typeAnalyseService.gettypeAnalyseById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeAnalyseDto.setId(id);
        Type_AnalyseDto updatedTypeAnalyse = typeAnalyseService.updatetypeAnalyse(typeAnalyseDto);
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
