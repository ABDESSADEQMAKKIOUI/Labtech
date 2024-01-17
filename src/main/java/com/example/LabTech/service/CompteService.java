package com.example.LabTech.service;


import com.example.LabTech.entite.Compte;
import com.example.LabTech.repository.CompteRepository;
import com.example.LabTech.service.interfaces.ICompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompteService implements ICompteService {
    @Autowired
    private CompteRepository compteRepository;

    // Méthodes de service pour la logique métier liée à Analyse
    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }
    @Override
    public Optional<Compte> getCompteById(long id) {
        return compteRepository.findById(id);
    }
    @Override
    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }
    @Override
    public Compte updateCompte(Compte compte) {
        return compteRepository.save(compte);
    }
    @Override
    public void deleteCompte(long id) {
        compteRepository.deleteById(id);
    }
}
