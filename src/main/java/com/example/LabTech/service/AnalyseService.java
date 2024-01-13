package com.example.LabTech.service;

import com.example.LabTech.entite.Analyse;

import com.example.LabTech.repository.AnalyseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    // Méthodes de service pour la logique métier liée à Analyse

    public List<Analyse> getAllAnalyses() {
        return analyseRepository.findAll();
    }

    public Optional<Analyse> getAnalyseById(long id) {
        return analyseRepository.findById(id);
    }

    public Analyse addAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }

    public Analyse updateAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }

    public void deleteAnalyse(long id) {
        analyseRepository.deleteById(id);
    }
}

