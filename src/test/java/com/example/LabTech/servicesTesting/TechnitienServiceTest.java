package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.repository.TechnitienRepository;
import com.example.LabTech.service.TechnitientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TechnitienServiceTest {

    @Mock
    private TechnitienRepository technitienRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TechnitientService technitienService;

    @Test
    void getAllTechnitiens() {
        // Arrange
        when(technitienRepository.findAll()).thenReturn(Arrays.asList(new Technitien(), new Technitien()));

        // Act
        List<TechnitienDto> result = technitienService.getAlltechnitiens();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void getTechnitienById() {
        // Arrange
        long technitienId = 1L;
        when(technitienRepository.findById(technitienId)).thenReturn(Optional.of(new Technitien()));
        when(modelMapper.map(any(), eq(TechnitienDto.class))).thenReturn(new TechnitienDto());

        // Act
        Optional<TechnitienDto> result = technitienService.gettechnitienById(technitienId);

        // Assert
        assertEquals(true, result.isPresent());
    }

    @Test
    void addTechnitien() {
        // Arrange
        TechnitienDto technitienDto = new TechnitienDto();
        Technitien technitien = new Technitien();
        when(modelMapper.map(technitienDto, Technitien.class)).thenReturn(technitien);
        when(modelMapper.map(technitien, TechnitienDto.class)).thenReturn(technitienDto);
        when(technitienRepository.save(technitien)).thenReturn(technitien);

        // Act
        TechnitienDto result = technitienService.addtechnitien(technitienDto);

        // Assert
        assertEquals(technitienDto, result);
    }

    @Test
    void updateTechnitien() {
        // Arrange
        TechnitienDto technitienDto = new TechnitienDto();
        Technitien technitien = new Technitien();
        when(modelMapper.map(technitienDto, Technitien.class)).thenReturn(technitien);
        when(modelMapper.map(technitien, TechnitienDto.class)).thenReturn(technitienDto);
        when(technitienRepository.save(technitien)).thenReturn(technitien);

        // Act
        TechnitienDto result = technitienService.updatetechnitien(technitienDto);

        // Assert
        assertEquals(technitienDto, result);
    }

    @Test
    void deleteTechnitien() {
        // Arrange
        long technitienId = 1L;

        // Act
        technitienService.deletetechnitien(technitienId);

        // Assert
        verify(technitienRepository, times(1)).deleteById(technitienId);
    }

    // Add more test methods as needed
}
