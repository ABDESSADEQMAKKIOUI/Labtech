package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.entite.Echantillon;

import java.util.List;
import java.util.Optional;

public interface IEchantillonService {
    List<EchantillonDto> getAllEchantillons();
    Optional<EchantillonDto> getEchantillonById(long id);
    EchantillonDto addEchantillon(EchantillonDto echantillon);
    EchantillonDto updateEchantillon(EchantillonDto echantillon);
    void deleteEchantillon(long id);
}
