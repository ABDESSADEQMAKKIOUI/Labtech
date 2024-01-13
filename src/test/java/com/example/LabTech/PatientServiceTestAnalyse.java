package com.example.LabTech;

import com.example.LabTech.entite.Patient;
import com.example.LabTech.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientServiceTestAnalyse {

    @Autowired
    private PatientService patientService;

    @Test
    public void testAddPatient() {
        // Arrange
        Patient patientToAdd = createSamplePatient();

        // Act
        Patient addedPatient = patientService.addPatient(patientToAdd);

        // Assert
        assertNotNull(addedPatient, "Added Patient should not be null");
        assertEquals(patientToAdd.getNom(), addedPatient.getNom(), "Nom should match");

    }

    @Test
    public void testFindPatientById() {
        // Arrange
        Patient patientToAdd = createSamplePatient();
        Patient addedPatient = patientService.addPatient(patientToAdd);

        // Act
        Optional<Patient> foundPatient = patientService.getPatientById(addedPatient.getId());

        // Assert
        assertTrue(foundPatient.isPresent(), "Patient should be present");
        assertEquals(addedPatient.getId(), foundPatient.get().getId(), "Found Patient ID should match the expected ID");

    }

    @Test
    public void testUpdatePatient() {
        // Arrange
        Patient patientToAdd = createSamplePatient();
        Patient addedPatient = patientService.addPatient(patientToAdd);

        // Act
        // Modify attributes of addedPatient
        addedPatient.setNom("UpdatedNom");
        Patient updatedPatient = patientService.updatePatient(addedPatient);

        // Assert
        assertNotNull(updatedPatient, "Updated Patient should not be null");
        assertEquals("UpdatedNom", updatedPatient.getNom(), "Nom should be updated");

    }

    @Test
    public void testDeletePatient() {
        // Arrange
        Patient patientToAdd = createSamplePatient();
        Patient addedPatient = patientService.addPatient(patientToAdd);

        // Act
        patientService.deletePatient(addedPatient.getId());

        // Assert
        Optional<Patient> deletedPatient = patientService.getPatientById(addedPatient.getId());
        assertFalse(deletedPatient.isPresent(), "Patient should be deleted");
    }

    @Test
    public void testGetAllPatients() {
        // Arrange
//        Patient patient1 = createSamplePatient();
//        Patient patient2 = createSamplePatient();
//        patientService.addPatient(patient1);
//        patientService.addPatient(patient2);

        // Act
        List<Patient> allPatients = patientService.getAllPatients();



        // Assert
        assertNotNull(allPatients, "List of Patients should not be null");
        assertEquals(1, allPatients.size(), "List size should match the expected size");

    }

    private Patient createSamplePatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("John");
        patient.setPrenom("Doe");
        patient.setAdress("123 Main St");
        patient.setEmail("john.doe@example.com");
        patient.setTele("123-456-7890");

        return patient;
    }
}

