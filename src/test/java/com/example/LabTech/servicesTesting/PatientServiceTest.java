package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.repository.PatientRepository;
import com.example.LabTech.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    void getAllPatientsTest() {



        // Appeler la méthode à tester
        List<PatientDto> result = patientService.getAllPatients();

        // Vérifier les assertions
       assertNotNull(result);
    }

    @Test
    void getPatientByIdTest() {
        // Ajouter un patient fictif à la base de données
        Patient patient = new Patient();
        patientRepository.save(patient);

        // Appeler la méthode à tester
        Optional<PatientDto> result = patientService.getPatientById(patient.getId());

        // Vérifier les assertions
        assertTrue(result.isPresent());
    }

    @Test
    void addPatientTest() {
        // Créer des objets PatientDto
        PatientDto patient1 = new PatientDto("Smith", "John", "john.smith@example.com");
        PatientDto patient2 = new PatientDto("Johnson", "Alice", "alice.johnson@example.com");
        PatientDto patient3 = new PatientDto("Doe", "Jane", "jane.doe@example.com");
        PatientDto patient4 = new PatientDto("Brown", "Bob", "bob.brown@example.com");
        PatientDto patient5 = new PatientDto("Taylor", "Emma", "emma.taylor@example.com");
        PatientDto patient6 = new PatientDto("Anderson", "Michael", "michael.anderson@example.com");

        // Appeler la méthode à tester
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient3);
        patientService.addPatient(patient4);
        patientService.addPatient(patient5);
        patientService.addPatient(patient6);

        // Vérifier que les patients ont été ajoutés à la base de données
        List<Patient> patients = patientRepository.findAll();
        assertEquals(6, patients.size());
    }

    @Test
    void updatePatientTest() {
        // Ajouter un patient fictif à la base de données
        Patient patient = new Patient();
        patientRepository.save(patient);

        // Créer un objet PatientDto avec les modifications
        PatientDto patientDto = new PatientDto();
        patientDto.setNom("Doe");

        // Appeler la méthode à tester
        PatientDto result = patientService.updatePatient(patientDto);

        // Vérifier les assertions
        assertNotNull(result);
        assertEquals(patientDto.getNom(), result.getNom());
    }

    @Test
    void deletePatientTest() {
        // Ajouter un patient fictif à la base de données
        Patient patient = new Patient();
        patientRepository.save(patient);

        // Appeler la méthode à tester
        patientService.deletePatient(patient.getId());

        // Vérifier que le patient a été supprimé de la base de données
        assertEquals(0, patientRepository.count());
    }
}
