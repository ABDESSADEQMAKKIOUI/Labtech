package com.example.LabTech.controller;

import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @GetMapping
    public List<ResponsableDto> getAllResponsables() {return responsableService.getAllResponsable();}

    @GetMapping("/{id}")
    public ResponseEntity<ResponsableDto> getResponsableById(@PathVariable long id) {
       return responsableService.getResponsableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponsableDto> addResponsable(@RequestBody ResponsableDto responsableDto) {
        ResponsableDto newResponsable = responsableService.addResponsable(responsableDto);
        return new ResponseEntity<>(newResponsable, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsableDto> updateResponsable(@PathVariable long id, @RequestBody ResponsableDto responsableDto) {
        if (!responsableService.getResponsableById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        responsableDto.setId(id);
        ResponsableDto updatedResponsable = responsableService.updateResponsable(responsableDto);
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
