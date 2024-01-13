package com.example.LabTech.service;

import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.repository.EchantillonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EchantillonService {

    @Autowired
    private EchantillonRepository echantillonRepository;

    // Méthodes de service pour la logique métier liée à Echantillon

    public List<Echantillon> getAllEchantillons() {
        return echantillonRepository.findAll();
    }

    public Optional<Echantillon> getEchantillonById(long id) {
        return echantillonRepository.findById(id);
    }

    public Echantillon addEchantillon(Echantillon echantillon) {
        return echantillonRepository.save(echantillon);
    }

    public Echantillon updateEchantillon(Echantillon echantillon) {
        return echantillonRepository.save(echantillon);
    }

    public void deleteEchantillon(long id) {
        echantillonRepository.deleteById(id);
    }
}

