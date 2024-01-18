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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
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
        PatientDto patientDto = new PatientDto();
        Patient patient = new Patient();
        when(modelMapper.map(patientDto, Patient.class)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);

        // Call the method to be tested
        PatientDto result = patientService.addPatient(patientDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(patientDto, Patient.class);
        verify(patientRepository, times(1)).save(patient);
        verify(modelMapper, times(1)).map(patient, PatientDto.class);
    }

    @Test
    void updatePatientTest() {
        // Mock the behavior of the repository
        PatientDto patientDto = new PatientDto();
        Patient existingPatient = new Patient();
        when(patientRepository.findById(patientDto.getId())).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(existingPatient)).thenReturn(existingPatient);

        // Call the method to be tested
        PatientDto result = patientService.updatePatient(patientDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(patientRepository, times(1)).findById(patientDto.getId());
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
