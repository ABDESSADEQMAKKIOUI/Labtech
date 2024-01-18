package com.example.LabTech.controller;

import com.example.LabTech.DTO.TestAnalyseDto;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<TestAnalyseDto> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestAnalyseDto> getTestById(@PathVariable long id) {
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TestAnalyseDto> addTest(@RequestBody TestAnalyseDto testAnalyseDto) {
        TestAnalyseDto addedTestAnalyse = testService.addTest(testAnalyseDto);
        return new ResponseEntity<>(addedTestAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestAnalyseDto> updateTest(@PathVariable long id, @RequestBody TestAnalyseDto testAnalyseDto) {
        if (!testService.getTestById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        testAnalyseDto.setId(id);
        TestAnalyseDto updatedTestAnalyse = testService.updateTest(testAnalyseDto);
        return new ResponseEntity<>(updatedTestAnalyse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable long id) {
        if (!testService.getTestById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        testService.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
