package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Echantillon;

import java.util.List;
import java.util.Optional;

public interface IEchantillonService {
    List<Echantillon> getAllEchantillons();
    Optional<Echantillon> getEchantillonById(long id);
    Echantillon addEchantillon(Echantillon echantillon);
    Echantillon updateEchantillon(Echantillon echantillon);
    void deleteEchantillon(long id);
}
