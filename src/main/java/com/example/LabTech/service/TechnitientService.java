package com.example.LabTech.service;

import com.example.LabTech.entite.Technitien;
import com.example.LabTech.repository.TechnitienRepository;
import com.example.LabTech.service.interfaces.ITechnitientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TechnitientService implements ITechnitientService {
    @Autowired
    private TechnitienRepository technitienRepository;

    // Méthodes de service pour la logique métier liée à Analyse
    @Override
    public List<Technitien> getAlltechnitiens() {
        return technitienRepository.findAll();
    }
    @Override
    public Optional<Technitien> gettechnitienById(long id) {
        return technitienRepository.findById(id);
    }
    @Override
    public Technitien addtechnitien(Technitien technitien) {
        return technitienRepository.save(technitien);
    }
    @Override
    public Technitien updatetechnitien(Technitien technitien) {
        return technitienRepository.save(technitien);
    }
    @Override
    public void deletetechnitien(long id) {
        technitienRepository.deleteById(id);
    }
}
