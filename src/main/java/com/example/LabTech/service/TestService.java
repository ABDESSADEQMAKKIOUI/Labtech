package com.example.LabTech.service;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService implements ITestService {

    @Autowired
    private TestRepository testRepository;

    // Méthodes de service pour la logique métier liée à Test
    @Override
    public List<Test_analyse> getAllTests() {
        return testRepository.findAll();
    }
    @Override
    public Optional<Test_analyse> getTestById(long id) {
        return testRepository.findById(id);
    }
    @Override
    public Test_analyse addTest(Test_analyse testAnalyse) {
        return testRepository.save(testAnalyse);
    }
    @Override
    public Test_analyse updateTest(Test_analyse testAnalyse) {
        return testRepository.save(testAnalyse);
    }
    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }
    @Override
    // Méthode pour récupérer les tests associés à une analyse
    public List<Test_analyse> getTestsByAnalyse(Analyse analyse) {
        return testRepository.findByAnalyse(analyse);
    }
}
