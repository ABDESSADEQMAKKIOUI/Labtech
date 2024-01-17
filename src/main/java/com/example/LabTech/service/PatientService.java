package com.example.LabTech.service;

import com.example.LabTech.entite.Patient;
import com.example.LabTech.repository.PatientRepository;
import com.example.LabTech.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Méthodes de service pour la logique métier liée à Patient
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @Override
    public Optional<Patient> getPatientById(long id) {
        return patientRepository.findById(id);
    }
    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }
}
