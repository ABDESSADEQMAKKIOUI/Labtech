package com.example.LabTech.service;

import com.example.LabTech.entite.RapportStatistique;
import com.example.LabTech.repository.RapportStatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapportStatistiqueService {

    @Autowired
    private RapportStatistiqueRepository rapportStatistiqueRepository;

    // Méthodes de service pour la logique métier liée à Rapport_Statistique

    public List<RapportStatistique> getAllRapportStatistiques() {
        return rapportStatistiqueRepository.findAll();
    }

    public Optional<RapportStatistique> getRapportStatistiqueById(long id) {
        return rapportStatistiqueRepository.findById(id);
    }

    public RapportStatistique addRapportStatistique(RapportStatistique rapportStatistique) {
        return rapportStatistiqueRepository.save(rapportStatistique);
    }

    public RapportStatistique updateRapportStatistique(RapportStatistique rapportStatistique) {
        return rapportStatistiqueRepository.save(rapportStatistique);
    }

    public void deleteRapportStatistique(long id) {
        rapportStatistiqueRepository.deleteById(id);
    }
}

