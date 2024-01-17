package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Patient;
import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(long id);

    Patient addPatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatient(long id);
}