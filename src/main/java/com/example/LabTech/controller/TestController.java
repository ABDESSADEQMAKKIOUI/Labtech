package com.example.LabTech.controller;

import com.example.LabTech.entite.Test_analyse;
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
    public List<Test_analyse> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test_analyse> getTestById(@PathVariable long id) {
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Test_analyse> addTest(@RequestBody Test_analyse testAnalyse) {
        Test_analyse addedTestAnalyse = testService.addTest(testAnalyse);
        return new ResponseEntity<>(addedTestAnalyse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test_analyse> updateTest(@PathVariable long id, @RequestBody Test_analyse testAnalyse) {
        if (testService.getTestById(id).isPresent()) {
            testAnalyse.setId(id);
            Test_analyse updatedTestAnalyse = testService.updateTest(testAnalyse);
            return ResponseEntity.ok(updatedTestAnalyse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable long id) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
