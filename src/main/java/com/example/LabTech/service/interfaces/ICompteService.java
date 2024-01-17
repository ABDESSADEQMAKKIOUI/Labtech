package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Compte;

import java.util.List;
import java.util.Optional;

public interface ICompteService {

    List<Compte> getAllComptes();

    Optional<Compte> getCompteById(long id);

    Compte addCompte(Compte compte);

    Compte updateCompte(Compte compte);

    void deleteCompte(long id);
}