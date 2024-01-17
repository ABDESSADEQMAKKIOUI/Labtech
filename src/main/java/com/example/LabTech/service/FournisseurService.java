package com.example.LabTech.service;

import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.repository.FournisseurRepository;
import com.example.LabTech.service.interfaces.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService implements IFournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    // Méthodes de service pour la logique métier liée à Fournisseur
    @Override
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }
    @Override
    public Optional<Fournisseur> getFournisseurById(long id) {
        return fournisseurRepository.findById(id);
    }
    @Override
    public Fournisseur addFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
    @Override
    public void deleteFournisseur(long id) {
        fournisseurRepository.deleteById(id);
    }
}