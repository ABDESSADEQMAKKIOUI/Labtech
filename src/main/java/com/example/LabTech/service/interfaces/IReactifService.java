package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.ReactifDto;
import com.example.LabTech.entite.Reactif;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReactifService {

    List<ReactifDto> getAllReactifs();

    Optional<ReactifDto> getReactifById(long id);

    ReactifDto addReactif(ReactifDto reactif);

    ReactifDto updateReactif(ReactifDto reactif);

    void deleteReactif(long id);

    boolean checkQuantity(long reactifId);

    boolean checkExpirationDate(long reactifId);
}