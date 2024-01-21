package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.repository.CompteRepository;
import com.example.LabTech.service.CompteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompteServiceTest {

    @Mock
    private CompteRepository compteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompteService compteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllComptesTest() {
        // Mock the behavior of the repository
        when(compteRepository.findAll()).thenReturn(Arrays.asList(new Compte(), new Compte()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(CompteDto.class))).thenReturn(new CompteDto());

        // Call the method to be tested
        List<CompteDto> result = compteService.getAllComptes();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(compteRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(CompteDto.class));
    }

    @Test
    void getCompteByIdTest() {
        // Mock the behavior of the repository
        when(compteRepository.findById(1L)).thenReturn(Optional.of(new Compte()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(CompteDto.class))).thenReturn(new CompteDto());

        // Call the method to be tested
        Optional<CompteDto> result = compteService.getCompteById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(compteRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(CompteDto.class));
    }

    @Test
    void addCompteTest() {
        // Mock the behavior of the repository
        CompteDto compteDto = new CompteDto();
        Compte compte = new Compte();
        when(modelMapper.map(compteDto, Compte.class)).thenReturn(compte);
        when(compteRepository.save(compte)).thenReturn(compte);

        // Call the method to be tested
        CompteDto result = compteService.addCompte(compteDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(compteDto, Compte.class);
        verify(compteRepository, times(1)).save(compte);
        verify(modelMapper, times(1)).map(compte, CompteDto.class);
    }

    @Test
    void updateCompteTest() {
        // Mock the behavior of the repository
        CompteDto compteDto = new CompteDto();
        Compte compte = new Compte();
        when(modelMapper.map(compteDto, Compte.class)).thenReturn(compte);
        when(compteRepository.save(compte)).thenReturn(compte);

        // Call the method to be tested
        CompteDto result = compteService.updateCompte(compteDto,compteDto.getId());

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(compteDto, Compte.class);
        verify(compteRepository, times(1)).save(compte);
        verify(modelMapper, times(1)).map(compte, CompteDto.class);
    }

    @Test
    void deleteCompteTest() {
        // Call the method to be tested
        compteService.deleteCompte(1L);

        // Verify the interactions
        verify(compteRepository, times(1)).deleteById(1L);
    }
}

