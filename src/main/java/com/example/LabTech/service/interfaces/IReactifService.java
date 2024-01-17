package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Reactif;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReactifService {

    List<Reactif> getAllReactifs();

    Optional<Reactif> getReactifById(long id);

    Reactif addReactif(Reactif reactif);

    Reactif updateReactif(Reactif reactif);

    void deleteReactif(long id);

    boolean checkQuantity(long reactifId);

    boolean checkExpirationDate(long reactifId);
}