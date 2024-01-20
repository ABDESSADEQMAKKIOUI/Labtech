package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.ReactifDto;

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
    void generateQuantity(int quantity , long id);
}