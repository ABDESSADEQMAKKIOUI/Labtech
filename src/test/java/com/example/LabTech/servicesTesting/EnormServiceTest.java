package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.service.EnormService;
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

public class EnormServiceTest {

    @Mock
    private EnormRepository enormRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EnormService enormService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEnormsTest() {
        // Mock the behavior of the repository
        when(enormRepository.findAll()).thenReturn(Arrays.asList(new Enorm(), new Enorm()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(EnormDto.class))).thenReturn(new EnormDto());

        // Call the method to be tested
        List<EnormDto> result = enormService.getAllEnorms();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(enormRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(EnormDto.class));
    }

    @Test
    void getEnormsByIdTest() {
        // Mock the behavior of the repository
        when(enormRepository.findById(1L)).thenReturn(Optional.of(new Enorm()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(EnormDto.class))).thenReturn(new EnormDto());

        // Call the method to be tested
        Optional<EnormDto> result = enormService.getEnormsById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(enormRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(EnormDto.class));
    }

    @Test
    void addEnormsTest() {
        // Mock the behavior of the repository
        EnormDto enormDto = new EnormDto();
        Enorm enorm = new Enorm();
        when(modelMapper.map(enormDto, Enorm.class)).thenReturn(enorm);
        when(enormRepository.save(enorm)).thenReturn(enorm);

        // Call the method to be tested
        EnormDto result = enormService.addEnorms(enormDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(enormDto, Enorm.class);
        verify(enormRepository, times(1)).save(enorm);
        verify(modelMapper, times(1)).map(enorm, EnormDto.class);
    }

    @Test
    void updateEnormsTest() {
        // Mock the behavior of the repository
        EnormDto enormDto = new EnormDto();
        Enorm existingEnorm = new Enorm();
        when(enormRepository.findById(enormDto.getId())).thenReturn(Optional.of(existingEnorm));
        when(enormRepository.save(existingEnorm)).thenReturn(existingEnorm);

        // Call the method to be tested
        EnormDto result = enormService.updateEnorms(enormDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(enormRepository, times(1)).findById(enormDto.getId());
        verify(modelMapper, times(1)).map(enormDto, existingEnorm);
        verify(enormRepository, times(1)).save(existingEnorm);
        verify(modelMapper, times(1)).map(existingEnorm, EnormDto.class);
    }

    @Test
    void deleteEnormsTest() {
        // Call the method to be tested
        enormService.deleteEnorms(1L);

        // Verify the interactions
        verify(enormRepository, times(1)).deleteById(1L);
    }
}
