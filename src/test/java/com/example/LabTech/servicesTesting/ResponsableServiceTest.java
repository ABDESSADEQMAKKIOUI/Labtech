package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.repository.ResponsbleRepository;
import com.example.LabTech.service.ResponsableService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ResponsableServiceTest {

    @Mock
    private ResponsbleRepository responsbleRepository;

    @Mock
    private ModelMapper modelMapper;

//    @InjectMocks
    @Autowired
    private ResponsableService responsableService;

    @Test
    void getAllResponsables() {
        // Arrange
        when(responsbleRepository.findAll()).thenReturn(Arrays.asList(new Responsable(), new Responsable()));

        // Act
        List<ResponsableDto> result = responsableService.getAllresponsable();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void getResponsableById() {
        // Arrange
        long responsableId = 1L;
        when(responsbleRepository.findById(responsableId)).thenReturn(Optional.of(new Responsable()));
        when(modelMapper.map(any(), eq(ResponsableDto.class))).thenReturn(new ResponsableDto());

        // Act
        Optional<ResponsableDto> result = responsableService.getResponsableById(responsableId);

        // Assert
        assertEquals(true, result.isPresent());
    }

    @Test
    void addResponsable() {
        // Arrange
//        ResponsableDto responsableDto = new ResponsableDto();
//        Responsable responsable = new Responsable();
//        when(modelMapper.map(responsableDto, Responsable.class)).thenReturn(responsable);
//        when(modelMapper.map(responsable, ResponsableDto.class)).thenReturn(responsableDto);
//        when(responsbleRepository.save(responsable)).thenReturn(responsable);

        // Act
        ResponsableDto responsable1 = new ResponsableDto();
        responsable1.setNom("Doe");
        responsable1.setPrenom("John");
        responsable1.setEmail("john.doe@example.com");

        ResponsableDto responsable2 = new ResponsableDto();
        responsable2.setNom("Smith");
        responsable2.setPrenom("Alice");
        responsable2.setEmail("alice.smith@example.com");
     responsableService.addResponsable(responsable1);
        responsableService.addResponsable(responsable2);


        // Assert

    }

    @Test
    void updateResponsable() {
        // Arrange
        ResponsableDto responsableDto = new ResponsableDto();
        Responsable responsable = new Responsable();
        when(modelMapper.map(responsableDto, Responsable.class)).thenReturn(responsable);
        when(modelMapper.map(responsable, ResponsableDto.class)).thenReturn(responsableDto);
        when(responsbleRepository.save(responsable)).thenReturn(responsable);

        // Act
        ResponsableDto result = responsableService.updateResponsable(responsableDto);

        // Assert
        assertEquals(responsableDto, result);
    }

    @Test
    void deleteResponsable() {
        // Arrange
        long responsableId = 1L;

        // Act
        responsableService.deleteResponsable(responsableId);

        // Assert
        verify(responsbleRepository, times(1)).deleteById(responsableId);
    }

    // Add more test methods as needed
}
