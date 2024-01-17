package com.example.LabTech.service;

import com.example.LabTech.entite.Reactif;
import com.example.LabTech.repository.ReactifRepository;
import com.example.LabTech.service.interfaces.IReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReactifService implements IReactifService {

    @Autowired
    private ReactifRepository reactifRepository;

    // Méthodes de service pour la logique métier liée à Reactif
    @Override
    public List<Reactif> getAllReactifs() {
        return reactifRepository.findAll();
    }
    @Override
    public Optional<Reactif> getReactifById(long id) {
        return reactifRepository.findById(id);
    }
    @Override
    public Reactif addReactif(Reactif reactif) {
        return reactifRepository.save(reactif);
    }
    @Override
    public Reactif updateReactif(Reactif reactif) {
        return reactifRepository.save(reactif);
    }
    @Override
    public void deleteReactif(long id) {
        reactifRepository.deleteById(id);
    }
    @Override
    public boolean checkQuantity(long reactifId) {
        // Récupérer le réactif depuis la base de données
        Reactif reactif = reactifRepository.findById(reactifId).orElse(null);

        // Vérifier si la quantité est inférieure à 5
        return reactif != null && reactif.getQuantity() >= 0 && reactif.getQuantity() < 5;
    }
    @Override
    public boolean checkExpirationDate(long reactifId) {
        // Récupérer le réactif depuis la base de données
        Reactif reactif = reactifRepository.findById(reactifId).orElse(null);

        // Vérifier si la date d'expiration est dans le futur
        Date currentDate = new Date();
        return reactif != null && reactif.getDate_expiration() != null && currentDate.before(reactif.getDate_expiration());
    }

}

