package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface IFournisseurService {

    List<Fournisseur> getAllFournisseurs();

    Optional<Fournisseur> getFournisseurById(long id);

    Fournisseur addFournisseur(Fournisseur fournisseur);

    Fournisseur updateFournisseur(Fournisseur fournisseur);

    void deleteFournisseur(long id);
}