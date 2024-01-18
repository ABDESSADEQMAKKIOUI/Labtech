package com.example.LabTech.service;

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.repository.PatientRepository;
import com.example.LabTech.service.interfaces.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDto> getPatientById(long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(this::convertToDto);
    }

    @Override
    public PatientDto addPatient(PatientDto patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        return convertToDto(patientRepository.save(patient));
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        return convertToDto(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }

    // Helper methods for mapping
    private PatientDto convertToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

    private Patient convertToEntity(PatientDto patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}
