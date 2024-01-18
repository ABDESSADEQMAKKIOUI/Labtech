package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.FournisseurDto;
import com.example.LabTech.entite.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface IFournisseurService {

    List<FournisseurDto> getAllFournisseurs();

    Optional<FournisseurDto> getFournisseurById(long id);

    FournisseurDto addFournisseur(FournisseurDto fournisseur);

    FournisseurDto updateFournisseur(FournisseurDto fournisseur);

    void deleteFournisseur(long id);
}