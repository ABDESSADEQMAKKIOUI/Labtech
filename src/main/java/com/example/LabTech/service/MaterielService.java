package com.example.LabTech.service;

import com.example.LabTech.entite.Materiel;
import com.example.LabTech.repository.MaterielRepository;
import com.example.LabTech.service.interfaces.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MaterielService implements IMaterielService {
    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public List<Materiel> getAllMateriel() {
        return materielRepository.findAll();
    }
    @Override
    public Optional<Materiel> getMaterielById(long id) {
        return materielRepository.findById(id);
    }
    @Override
    public Materiel addMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }
    @Override
    public Materiel updateMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }
    @Override
    public void deleteMateriel(long id) {
        materielRepository.deleteById(id);
    }
}
