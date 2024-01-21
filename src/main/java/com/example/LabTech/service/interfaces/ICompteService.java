package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.entite.Compte;

import java.util.List;
import java.util.Optional;

public interface ICompteService {

    List<CompteDto> getAllComptes();

    Optional<CompteDto> getCompteById(long id);

    CompteDto addCompte(CompteDto compte);

    CompteDto updateCompte(CompteDto compte);

    void deleteCompte(long id);
}