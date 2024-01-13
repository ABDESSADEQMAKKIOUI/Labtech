package com.example.LabTech.service;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    // Méthodes de service pour la logique métier liée à Test

    public List<Test_analyse> getAllTests() {
        return testRepository.findAll();
    }

    public Optional<Test_analyse> getTestById(long id) {
        return testRepository.findById(id);
    }

    public Test_analyse addTest(Test_analyse testAnalyse) {
        return testRepository.save(testAnalyse);
    }

    public Test_analyse updateTest(Test_analyse testAnalyse) {
        return testRepository.save(testAnalyse);
    }

    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    // Méthode pour récupérer les tests associés à une analyse
    public List<Test_analyse> getTestsByAnalyse(Analyse analyse) {
        return testRepository.findByAnalyse(analyse);
    }
}
