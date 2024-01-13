package com.example.LabTech.service;


import com.example.LabTech.entite.Compte;
import com.example.LabTech.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    // Méthodes de service pour la logique métier liée à Analyse

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompteById(long id) {
        return compteRepository.findById(id);
    }

    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Compte updateCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public void deleteCompte(long id) {
        compteRepository.deleteById(id);
    }
}
