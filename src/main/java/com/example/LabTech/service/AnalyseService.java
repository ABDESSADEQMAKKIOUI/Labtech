package com.example.LabTech.service;

import com.example.LabTech.entite.Analyse;

import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.interfaces.IAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalyseService implements IAnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;
    @Autowired
    private TestRepository testAnalyseRepository;
    @Autowired
    private EnormRepository enormRepository ;

    // Méthodes de service pour la logique métier liée à Analyse

    @Override
    public List<Analyse> getAllAnalyses() {
        return analyseRepository.findAll();
    }
    @Override
    public Optional<Analyse> getAnalyseById(long id) {
        return analyseRepository.findById(id);
    }
    @Override
    public Analyse addAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }
    @Override
    public Analyse updateAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }
    @Override
    public void deleteAnalyse(long id) {
        analyseRepository.deleteById(id);
    }
    @Override
    public List<Enorm> getEnormsByTypeAnalyseAndCreateTests(Type_Analyse typeAnalyse, Analyse analyse) {
        List<Enorm> norms = enormRepository.findByTypeAnalyseId(typeAnalyse.getId());

        List<Test_analyse> tests = norms.stream()
                .map(enorm -> {
                    Test_analyse testAnalyse = new Test_analyse();
                    // Configurer les propriétés du testAnalyse en fonction des besoins
                    testAnalyse.setEnorm(enorm);
                    testAnalyse.setAnalyse(analyse);
                    return testAnalyse;
                })
                .collect(Collectors.toList());

        testAnalyseRepository.saveAll(tests);

        return norms;
    }
}

