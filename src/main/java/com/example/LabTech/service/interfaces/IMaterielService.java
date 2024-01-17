package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Materiel;

import java.util.List;
import java.util.Optional;

public interface IMaterielService {

    List<Materiel> getAllMateriel();

    Optional<Materiel> getMaterielById(long id);

    Materiel addMateriel(Materiel materiel);

    Materiel updateMateriel(Materiel materiel);

    void deleteMateriel(long id);
}
