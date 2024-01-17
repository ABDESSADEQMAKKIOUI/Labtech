package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Enorm;

import java.util.List;
import java.util.Optional;

public interface IEnormService {

    List<Enorm> getAllEnorms();

    Optional<Enorm> getEnormsById(long id);

    Enorm addEnorms(Enorm enorm);

    Enorm updateEnorms(Enorm enorm);

    void deleteEnorms(long id);
}