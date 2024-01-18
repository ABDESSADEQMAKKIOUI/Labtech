package com.example.LabTech.controller;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.service.EnormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enorms")
public class EnormController {

    @Autowired
    private EnormService enormService;

    @GetMapping
    public List<EnormDto> getAllEnorms() {
        return enormService.getAllEnorms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnormDto> getEnormById(@PathVariable long id) {
        return enormService.getEnormsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnormDto> addEnorm(@RequestBody EnormDto enormDto) {
        EnormDto newEnorm = enormService.addEnorms(enormDto);
        return new ResponseEntity<>(newEnorm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnormDto> updateEnorm(@PathVariable long id, @RequestBody EnormDto enormDto) {
        if (enormService.getEnormsById(id).isPresent()) {
            enormDto.setId(id);
            EnormDto updatedEnorm = enormService.updateEnorms(enormDto);
            return ResponseEntity.ok(updatedEnorm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnorm(@PathVariable long id) {
        enormService.deleteEnorms(id);
        return ResponseEntity.noContent().build();
    }
}
