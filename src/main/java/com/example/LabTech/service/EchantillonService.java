package com.example.LabTech.service;

import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.repository.EchantillonRepository;
import com.example.LabTech.service.interfaces.IEchantillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EchantillonService implements IEchantillonService {

    @Autowired
    private EchantillonRepository echantillonRepository;

    // Méthodes de service pour la logique métier liée à Echantillon
    @Override
    public List<Echantillon> getAllEchantillons() {
        return echantillonRepository.findAll();
    }
    @Override
    public Optional<Echantillon> getEchantillonById(long id) {
        return echantillonRepository.findById(id);
    }
    @Override
    public Echantillon addEchantillon(Echantillon echantillon) {
        return echantillonRepository.save(echantillon);
    }
    @Override
    public Echantillon updateEchantillon(Echantillon echantillon) {
        return echantillonRepository.save(echantillon);
    }
    @Override
    public void deleteEchantillon(long id) {
        echantillonRepository.deleteById(id);
    }
}

