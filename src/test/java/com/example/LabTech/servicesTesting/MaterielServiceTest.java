package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.MaterielDto;
import com.example.LabTech.entite.Materiel;
import com.example.LabTech.repository.MaterielRepository;
import com.example.LabTech.service.MaterielService;
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

public class MaterielServiceTest {

    @Mock
    private MaterielRepository materielRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MaterielService materielService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMaterielTest() {
        // Mock the behavior of the repository
        when(materielRepository.findAll()).thenReturn(Arrays.asList(new Materiel(), new Materiel()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(MaterielDto.class))).thenReturn(new MaterielDto());

        // Call the method to be tested
        List<MaterielDto> result = materielService.getAllMateriel();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(materielRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(MaterielDto.class));
    }

    @Test
    void getMaterielByIdTest() {
        // Mock the behavior of the repository
        when(materielRepository.findById(1L)).thenReturn(Optional.of(new Materiel()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(MaterielDto.class))).thenReturn(new MaterielDto());

        // Call the method to be tested
        Optional<MaterielDto> result = materielService.getMaterielById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(materielRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(MaterielDto.class));
    }

    @Test
    void addMaterielTest() {
        // Mock the behavior of the repository
        MaterielDto materielDto = new MaterielDto();
        Materiel materiel = new Materiel();
        when(modelMapper.map(materielDto, Materiel.class)).thenReturn(materiel);
        when(materielRepository.save(materiel)).thenReturn(materiel);

        // Call the method to be tested
        MaterielDto result = materielService.addMateriel(materielDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(materielDto, Materiel.class);
        verify(materielRepository, times(1)).save(materiel);
        verify(modelMapper, times(1)).map(materiel, MaterielDto.class);
    }

    @Test
    void updateMaterielTest() {
        // Mock the behavior of the repository
        MaterielDto materielDto = new MaterielDto();
        Materiel existingMateriel = new Materiel();
        when(materielRepository.findById(materielDto.getId())).thenReturn(Optional.of(existingMateriel));
        when(materielRepository.save(existingMateriel)).thenReturn(existingMateriel);

        // Call the method to be tested
        MaterielDto result = materielService.updateMateriel(materielDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(materielRepository, times(1)).findById(materielDto.getId());
        verify(modelMapper, times(1)).map(materielDto, existingMateriel);
        verify(materielRepository, times(1)).save(existingMateriel);
        verify(modelMapper, times(1)).map(existingMateriel, MaterielDto.class);
    }

    @Test
    void deleteMaterielTest() {
        // Call the method to be tested
        materielService.deleteMateriel(1L);

        // Verify the interactions
        verify(materielRepository, times(1)).deleteById(1L);
    }
}
