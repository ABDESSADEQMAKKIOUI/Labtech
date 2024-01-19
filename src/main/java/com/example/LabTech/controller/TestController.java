package com.example.LabTech.controller;

import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test_analyseDto> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test_analyseDto> getTestById(@PathVariable long id) {
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Test_analyseDto> addTest(@RequestBody Test_analyseDto testAnalyseDto) {
        Test_analyseDto addedTestAnalyse = testService.addTest(testAnalyseDto);
        return new ResponseEntity<>(addedTestAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test_analyseDto> updateTest(@PathVariable long id, @RequestBody Test_analyseDto testAnalyseDto) {
        if (!testService.getTestById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        testAnalyseDto.setId(id);
        Test_analyseDto updatedTestAnalyse = testService.updateTest(testAnalyseDto);
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
