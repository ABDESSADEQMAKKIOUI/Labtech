package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.repository.EchantillonRepository;
import com.example.LabTech.service.EchantillonService;
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

public class EchantillonServiceTest {

    @Mock
    private EchantillonRepository echantillonRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EchantillonService echantillonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEchantillonsTest() {
        // Mock the behavior of the repository
        when(echantillonRepository.findAll()).thenReturn(Arrays.asList(new Echantillon(), new Echantillon()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(EchantillonDto.class))).thenReturn(new EchantillonDto());

        // Call the method to be tested
        List<EchantillonDto> result = echantillonService.getAllEchantillons();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(echantillonRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(EchantillonDto.class));
    }

    @Test
    void getEchantillonByIdTest() {
        // Mock the behavior of the repository
        when(echantillonRepository.findById(1L)).thenReturn(Optional.of(new Echantillon()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(EchantillonDto.class))).thenReturn(new EchantillonDto());

        // Call the method to be tested
        Optional<EchantillonDto> result = echantillonService.getEchantillonById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(echantillonRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(EchantillonDto.class));
    }

    @Test
    void addEchantillonTest() {
        // Mock the behavior of the repository
        EchantillonDto echantillonDto = new EchantillonDto();
        Echantillon echantillon = new Echantillon();
        when(modelMapper.map(echantillonDto, Echantillon.class)).thenReturn(echantillon);
        when(echantillonRepository.save(echantillon)).thenReturn(echantillon);

        // Call the method to be tested
        EchantillonDto result = echantillonService.addEchantillon(echantillonDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(echantillonDto, Echantillon.class);
        verify(echantillonRepository, times(1)).save(echantillon);
        verify(modelMapper, times(1)).map(echantillon, EchantillonDto.class);
    }

    @Test
    void updateEchantillonTest() {
        // Mock the behavior of the repository
        EchantillonDto echantillonDto = new EchantillonDto();
        Echantillon echantillon = new Echantillon();
        when(modelMapper.map(echantillonDto, Echantillon.class)).thenReturn(echantillon);
        when(echantillonRepository.save(echantillon)).thenReturn(echantillon);

        // Call the method to be tested
        EchantillonDto result = echantillonService.updateEchantillon(echantillonDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(echantillonDto, Echantillon.class);
        verify(echantillonRepository, times(1)).save(echantillon);
        verify(modelMapper, times(1)).map(echantillon, EchantillonDto.class);
    }

    @Test
    void deleteEchantillonTest() {
        // Call the method to be tested
        echantillonService.deleteEchantillon(1L);

        // Verify the interactions
        verify(echantillonRepository, times(1)).deleteById(1L);
    }
}
