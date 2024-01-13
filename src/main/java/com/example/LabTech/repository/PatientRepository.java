package com.example.LabTech.repository;

import com.example.LabTech.entite.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}

