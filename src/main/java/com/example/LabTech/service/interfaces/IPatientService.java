package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<PatientDto> getAllPatients();

    Optional<PatientDto> getPatientById(long id);

    PatientDto addPatient(PatientDto patient);

    PatientDto updatePatient(PatientDto patient);

    void deletePatient(long id);
}