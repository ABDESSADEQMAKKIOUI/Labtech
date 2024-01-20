package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.repository.PatientRepository;
import com.example.LabTech.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatientsTest() {
        // Mock the behavior of the repository
        when(patientRepository.findAll()).thenReturn(Arrays.asList(new Patient(), new Patient()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(PatientDto.class))).thenReturn(new PatientDto());

        // Call the method to be tested
        List<PatientDto> result = patientService.getAllPatients();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(PatientDto.class));
    }

    @Test
    void getPatientByIdTest() {
        // Mock the behavior of the repository
        when(patientRepository.findById(1L)).thenReturn(Optional.of(new Patient()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(PatientDto.class))).thenReturn(new PatientDto());

        // Call the method to be tested
        Optional<PatientDto> result = patientService.getPatientById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(patientRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(PatientDto.class));
    }

    @Test
    void addPatientTest() {
        // Mock the behavior of the repository
        PatientDto patient1 = new PatientDto( "Smith", "John", "john.smith@example.com");
        PatientDto patient2 = new PatientDto( "Johnson", "Alice", "alice.johnson@example.com");
        PatientDto patient3 = new PatientDto( "Doe", "Jane", "jane.doe@example.com");
        PatientDto patient4 = new PatientDto( "Brown", "Bob", "bob.brown@example.com");
        PatientDto patient5 = new PatientDto( "Taylor", "Emma", "emma.taylor@example.com");
        PatientDto patient6 = new PatientDto( "Anderson", "Michael", "michael.anderson@example.com");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient6);
        patientService.addPatient(patient3);
        patientService.addPatient(patient4);
        patientService.addPatient(patient5);
    }

    @Test
    void updatePatientTest() {
        // Mock the behavior of the repository
        PatientDto patientDto = new PatientDto();
        Patient existingPatient = new Patient();
//        when(patientRepository.findById(patientDto.getId())).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(existingPatient)).thenReturn(existingPatient);

        // Call the method to be tested
        PatientDto result = patientService.updatePatient(patientDto);

        // Verify the interactions and assertions
        assertNotNull(result);
//        verify(patientRepository, times(1)).findById(patientDto.getId());
        verify(modelMapper, times(1)).map(patientDto, existingPatient);
        verify(patientRepository, times(1)).save(existingPatient);
        verify(modelMapper, times(1)).map(existingPatient, PatientDto.class);
    }

    @Test
    void deletePatientTest() {
        // Call the method to be tested
        patientService.deletePatient(1L);

        // Verify the interactions
        verify(patientRepository, times(1)).deleteById(1L);
    }
}
