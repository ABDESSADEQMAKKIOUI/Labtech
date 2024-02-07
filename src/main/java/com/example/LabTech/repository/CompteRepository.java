package com.example.LabTech.repository;


import com.example.LabTech.entite.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    Optional<Compte> findByUsername(String username);
}
