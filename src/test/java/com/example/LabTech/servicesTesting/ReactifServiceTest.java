package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.ReactifDto;
import com.example.LabTech.entite.Reactif;
import com.example.LabTech.repository.ReactifRepository;
import com.example.LabTech.service.ReactifService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReactifServiceTest {

    @Mock
    private ReactifRepository reactifRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReactifService reactifService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReactifsTest() {
        // Mock the behavior of the repository
        when(reactifRepository.findAll()).thenReturn(Arrays.asList(new Reactif(), new Reactif()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(ReactifDto.class))).thenReturn(new ReactifDto());

        // Call the method to be tested
        List<ReactifDto> result = reactifService.getAllReactifs();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(reactifRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(ReactifDto.class));
    }

    @Test
    void getReactifByIdTest() {
        // Mock the behavior of the repository
        when(reactifRepository.findById(1L)).thenReturn(Optional.of(new Reactif()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(ReactifDto.class))).thenReturn(new ReactifDto());

        // Call the method to be tested
        Optional<ReactifDto> result = reactifService.getReactifById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(reactifRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(ReactifDto.class));
    }

    @Test
    void addReactifTest() {
        // Mock the behavior of the repository
        ReactifDto reactifDto = new ReactifDto();
        Reactif reactif = new Reactif();
        when(modelMapper.map(reactifDto, Reactif.class)).thenReturn(reactif);
        when(reactifRepository.save(reactif)).thenReturn(reactif);

        // Call the method to be tested
        ReactifDto result = reactifService.addReactif(reactifDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(reactifDto, Reactif.class);
        verify(reactifRepository, times(1)).save(reactif);
        verify(modelMapper, times(1)).map(reactif, ReactifDto.class);
    }

    @Test
    void updateReactifTest() {
        // Mock the behavior of the repository
        ReactifDto reactifDto = new ReactifDto();
        Reactif existingReactif = new Reactif();
        when(reactifRepository.findById(reactifDto.getId())).thenReturn(Optional.of(existingReactif));
//        when(modelMapper.map(reactifDto, existingReactif)).thenReturn(existingReactif);
        when(reactifRepository.save(existingReactif)).thenReturn(existingReactif);

        // Call the method to be tested
        ReactifDto result = reactifService.updateReactif(reactifDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(reactifRepository, times(1)).findById(reactifDto.getId());
        verify(modelMapper, times(1)).map(reactifDto, existingReactif);
        verify(reactifRepository, times(1)).save(existingReactif);
        verify(modelMapper, times(1)).map(existingReactif, ReactifDto.class);
    }

    @Test
    void deleteReactifTest() {
        // Call the method to be tested
        reactifService.deleteReactif(1L);

        // Verify the interactions
        verify(reactifRepository, times(1)).deleteById(1L);
    }

    @Test
    void checkQuantityTest() {
        // Mock the behavior of the repository
        Reactif reactif = new Reactif();
        when(reactifRepository.findById(1L)).thenReturn(Optional.of(reactif));

        // Call the method to be tested
        boolean result = reactifService.checkQuantity(1L);

        // Verify the interactions and assertions
        assertTrue(result);
        verify(reactifRepository, times(1)).findById(1L);
    }

    @Test
    void checkExpirationDateTest() {
        // Mock the behavior of the repository
        Reactif reactif = new Reactif();
        reactif.setDate_expiration(new Date(System.currentTimeMillis() + 86400000)); // set date_expiration to be one day in the future
        when(reactifRepository.findById(1L)).thenReturn(Optional.of(reactif));

        // Call the method to be tested
        boolean result = reactifService.checkExpirationDate(1L);

        // Verify the interactions and assertions
        assertTrue(result);
        verify(reactifRepository, times(1)).findById(1L);
    }

    // Additional tests for other methods
}
