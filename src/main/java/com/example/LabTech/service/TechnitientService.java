package com.example.LabTech.service;

import com.example.LabTech.entite.Technitien;
import com.example.LabTech.repository.TechnitienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TechnitientService {
    @Autowired
    private TechnitienRepository technitienRepository;

    // Méthodes de service pour la logique métier liée à Analyse

    public List<Technitien> getAlltechnitiens() {
        return technitienRepository.findAll();
    }

    public Optional<Technitien> gettechnitienById(long id) {
        return technitienRepository.findById(id);
    }

    public Technitien addtechnitien(Technitien technitien) {
        return technitienRepository.save(technitien);
    }

    public Technitien updatetechnitien(Technitien technitien) {
        return technitienRepository.save(technitien);
    }

    public void deletetechnitien(long id) {
        technitienRepository.deleteById(id);
    }
}
