package com.example.LabTech.repository;

import com.example.LabTech.entite.Technitien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnitienRepository extends JpaRepository<Technitien, Long> {
}
