package com.example.LabTech.service;


import com.example.LabTech.entite.Enorm;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.service.interfaces.IEnormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EnormService implements IEnormService {
    @Autowired
    private EnormRepository enormRepository;

    @Override
    public List<Enorm> getAllEnorms() {
        return enormRepository.findAll();
    }
    @Override
    public Optional<Enorm> getEnormsById(long id) {
        return enormRepository.findById(id);
    }
    @Override
    public Enorm addEnorms(Enorm enorm) {return enormRepository.save(enorm); }
    @Override
    public Enorm updateEnorms(Enorm enorm) {
        return enormRepository.save(enorm);
    }
    @Override
    public void deleteEnorms(long id) {
        enormRepository.deleteById(id);
    }
}

