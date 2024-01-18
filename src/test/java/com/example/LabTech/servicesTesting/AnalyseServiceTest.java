package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.AnalyseService;
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

public class AnalyseServiceTest {

    @Mock
    private AnalyseRepository analyseRepository;

    @Mock
    private TestRepository testRepository;

    @Mock
    private EnormRepository enormRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AnalyseService analyseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAnalysesTest() {
        // Mock the behavior of the repository
        when(analyseRepository.findAll()).thenReturn(Arrays.asList(new Analyse(), new Analyse()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(AnalyseDto.class))).thenReturn(new AnalyseDto());

        // Call the method to be tested
        List<AnalyseDto> result = analyseService.getAllAnalyses();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(analyseRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(AnalyseDto.class));
    }

    @Test
    void getAnalyseByIdTest() {
        // Mock the behavior of the repository
        when(analyseRepository.findById(1L)).thenReturn(Optional.of(new Analyse()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(AnalyseDto.class))).thenReturn(new AnalyseDto());

        // Call the method to be tested
        Optional<AnalyseDto> result = analyseService.getAnalyseById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(analyseRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(AnalyseDto.class));
    }

    @Test
    void addAnalyseTest() {
        // Mock the behavior of the repository
        AnalyseDto analyseDto = new AnalyseDto();
        Analyse analyse = new Analyse();
        when(modelMapper.map(analyseDto, Analyse.class)).thenReturn(analyse);
        when(analyseRepository.save(analyse)).thenReturn(analyse);

        // Call the method to be tested
        AnalyseDto result = analyseService.addAnalyse(analyseDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(modelMapper, times(1)).map(analyseDto, Analyse.class);
        verify(analyseRepository, times(1)).save(analyse);
        verify(modelMapper, times(1)).map(analyse, AnalyseDto.class);
    }

    @Test
    void updateAnalyseTest() {
        // Mock the behavior of the repository
        long id = 1L;
        AnalyseDto analyseDto = new AnalyseDto();
        Analyse existingAnalyse = new Analyse();
        when(analyseRepository.findById(id)).thenReturn(Optional.of(existingAnalyse));
        when(modelMapper.map(analyseDto, Analyse.class)).thenReturn(existingAnalyse);
        when(analyseRepository.save(existingAnalyse)).thenReturn(existingAnalyse);

        // Call the method to be tested
        AnalyseDto result = analyseService.updateAnalyse(analyseDto, id);

        // Verify the interactions and assertions
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(analyseRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(analyseDto, Analyse.class);
        verify(analyseRepository, times(1)).save(existingAnalyse);
        verify(modelMapper, times(1)).map(existingAnalyse, AnalyseDto.class);
    }

    @Test
    void deleteAnalyseTest() {
        // Call the method to be tested
        analyseService.deleteAnalyse(1L);

        // Verify the interactions
        verify(analyseRepository, times(1)).deleteById(1L);
    }

    @Test
    void getEnormsByTypeAnalyseAndCreateTestsTest() {
        // Mock the behavior of the repository
        long typeAnalyseId = 1L;
        AnalyseDto analyseDto = new AnalyseDto();
        Type_Analyse typeAnalyse = new Type_Analyse();
        typeAnalyse.setId(typeAnalyseId);
        when(enormRepository.findByTypeAnalyseId(typeAnalyseId)).thenReturn(Arrays.asList(new Enorm(), new Enorm()));
        when(modelMapper.map(any(), eq(EnormDto.class))).thenReturn(new EnormDto());

        // Call the method to be tested
        List<EnormDto> result = analyseService.getEnormsByTypeAnalyseAndCreateTests(typeAnalyseId, analyseDto);

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(enormRepository, times(1)).findByTypeAnalyseId(typeAnalyseId);
        verify(modelMapper, times(2)).map(any(), eq(EnormDto.class));
        verify(testRepository, times(1)).saveAll(anyList());
    }
}
