package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.entite.Enorm;

import java.util.List;
import java.util.Optional;

public interface IEnormService {

    List<EnormDto> getAllEnorms();

    Optional<EnormDto> getEnormsById(long id);

    EnormDto addEnorms(EnormDto enorm);

    EnormDto updateEnorms(EnormDto enorm);

    void deleteEnorms(long id);
}