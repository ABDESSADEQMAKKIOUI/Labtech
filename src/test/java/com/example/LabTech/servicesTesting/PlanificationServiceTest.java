package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.DTO.TestAnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.PlanificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.mockito.Mockito.*;

public class PlanificationServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private AnalyseRepository analyseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlanificationService planificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void affecterTechnicienTest() {
        // Mock the behavior of the ModelMapper
        TechnitienDto technitienDto = new TechnitienDto();
        TestAnalyseDto testAnalyseDto = new TestAnalyseDto();
        Technitien technitien = new Technitien();
        Test_analyse testAnalyse = new Test_analyse();
        when(modelMapper.map(technitienDto, Technitien.class)).thenReturn(technitien);
        when(modelMapper.map(testAnalyseDto, Test_analyse.class)).thenReturn(testAnalyse);

        // Call the method to be tested
        planificationService.affecterTechnicien(technitienDto, testAnalyseDto);

        // Verify the interactions
        verify(testAnalyse, times(1)).setTechnitien(technitien);
        verify(testRepository, times(1)).save(testAnalyse);
    }

    @Test
    void ChangerDateAnalyseTest() {
        // Mock the behavior of the ModelMapper
        AnalyseDto analyseDto = new AnalyseDto();
        Date dateDebut = new Date();
        Date dateFin = new Date();
        Analyse analyse = new Analyse();
        when(modelMapper.map(analyseDto, Analyse.class)).thenReturn(analyse);

        // Call the method to be tested
        planificationService.ChangerDateAnalyse(analyseDto, dateDebut, dateFin);

        // Verify the interactions
        verify(analyse, times(1)).setDate_debut(dateDebut);
        verify(analyse, times(1)).setDate_fin(dateFin);
        verify(analyseRepository, times(1)).save(analyse);
    }

    @Test
    void ChangerTechnicienTest() {
        // Mock the behavior of the ModelMapper
        TechnitienDto technitienDto = new TechnitienDto();
        TestAnalyseDto testAnalyseDto = new TestAnalyseDto();
        Technitien technitien = new Technitien();
        Test_analyse testAnalyse = new Test_analyse();
        when(modelMapper.map(technitienDto, Technitien.class)).thenReturn(technitien);
        when(modelMapper.map(testAnalyseDto, Test_analyse.class)).thenReturn(testAnalyse);

        // Call the method to be tested
        planificationService.ChangerTechnicien(technitienDto, testAnalyseDto);

        // Verify the interactions
        verify(testAnalyse, times(1)).setTechnitien(technitien);
        verify(testRepository, times(1)).save(testAnalyse);
    }

    @Test
    void ChangerResponsableTest() {
        // Mock the behavior of the ModelMapper
        ResponsableDto responsableDto = new ResponsableDto();
        AnalyseDto analyseDto = new AnalyseDto();
        Responsable responsable = new Responsable();
        Analyse analyse = new Analyse();
        when(modelMapper.map(responsableDto, Responsable.class)).thenReturn(responsable);
        when(modelMapper.map(analyseDto, Analyse.class)).thenReturn(analyse);

        // Call the method to be tested
        planificationService.ChangerResponsable(responsableDto, analyseDto);

        // Verify the interactions
        verify(analyse, times(1)).setResponsable(responsable);
        verify(analyseRepository, times(1)).save(analyse);
    }

    @Test
    void AjouterTestTest() {
        // Mock the behavior of the ModelMapper
        AnalyseDto analyseDto = new AnalyseDto();
        TestAnalyseDto testAnalyseDto = new TestAnalyseDto();
        Analyse analyse = new Analyse();
        Test_analyse testAnalyse = new Test_analyse();
        when(modelMapper.map(analyseDto, Analyse.class)).thenReturn(analyse);
        when(modelMapper.map(testAnalyseDto, Test_analyse.class)).thenReturn(testAnalyse);

        // Call the method to be tested
        planificationService.AjouterTest(analyseDto, testAnalyseDto);

        // Verify the interactions
        verify(analyse, times(1)).getTestAnalyses();
        verify(analyse, times(1)).setTestAnalyses(any());
        verify(analyseRepository, times(1)).save(analyse);
    }
}
