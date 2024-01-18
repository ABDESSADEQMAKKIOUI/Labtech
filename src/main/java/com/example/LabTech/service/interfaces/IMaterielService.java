package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.MaterielDto;
import com.example.LabTech.entite.Materiel;

import java.util.List;
import java.util.Optional;

public interface IMaterielService {

    List<MaterielDto> getAllMateriel();

    Optional<MaterielDto> getMaterielById(long id);

    MaterielDto addMateriel(MaterielDto materiel);

    MaterielDto updateMateriel(MaterielDto materiel);

    void deleteMateriel(long id);
}
