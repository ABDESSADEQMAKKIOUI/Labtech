package com.example.LabTech.repository;


import com.example.LabTech.entite.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
