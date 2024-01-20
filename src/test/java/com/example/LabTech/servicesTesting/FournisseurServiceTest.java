package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.FournisseurDto;
import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.repository.FournisseurRepository;
import com.example.LabTech.service.FournisseurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
public class FournisseurServiceTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ModelMapper modelMapper;

//    @InjectMocks
    @Autowired
    private FournisseurService fournisseurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFournisseursTest() {
        // Mock the behavior of the repository
        when(fournisseurRepository.findAll()).thenReturn(Arrays.asList(new Fournisseur(), new Fournisseur()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(FournisseurDto.class))).thenReturn(new FournisseurDto());

        // Call the method to be tested
        List<FournisseurDto> result = fournisseurService.getAllFournisseurs();

        // Verify the interactions and assertions
        assertEquals(2, result.size());
        verify(fournisseurRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), eq(FournisseurDto.class));
    }

    @Test
    void getFournisseurByIdTest() {
        // Mock the behavior of the repository
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(new Fournisseur()));

        // Mock the behavior of the ModelMapper
        when(modelMapper.map(any(), eq(FournisseurDto.class))).thenReturn(new FournisseurDto());

        // Call the method to be tested
        Optional<FournisseurDto> result = fournisseurService.getFournisseurById(1L);

        // Verify the interactions and assertions
        assertTrue(result.isPresent());
        verify(fournisseurRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), eq(FournisseurDto.class));
    }

    @Test
    void addFournisseurTest() {
        // Mock the behavior of the repository
        FournisseurDto fournisseurDto = new FournisseurDto();
        FournisseurDto fournisseurDto1 = new FournisseurDto();
        FournisseurDto fournisseurDto2 = new FournisseurDto();
        FournisseurDto fournisseurDto3 = new FournisseurDto();
        FournisseurDto fournisseurDto4 = new FournisseurDto();

        fournisseurDto.setNom("fournisseurDto");
        fournisseurDto1.setNom("fournisseurDto1");
        fournisseurDto2.setNom("fournisseurDto2");
        fournisseurDto3.setNom("fournisseurDto3");
        fournisseurDto4.setNom("fournisseurDto4");
//        Fournisseur fournisseur = new Fournisseur();
//        when(modelMapper.map(fournisseurDto, Fournisseur.class)).thenReturn(fournisseur);
//        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        // Call the method to be tested
        fournisseurService.addFournisseur(fournisseurDto);
        fournisseurService.addFournisseur(fournisseurDto1);
        fournisseurService.addFournisseur(fournisseurDto2);
        fournisseurService.addFournisseur(fournisseurDto3);
        fournisseurService.addFournisseur(fournisseurDto4);


//        // Verify the interactions and assertions
//        assertNotNull(result);
//        verify(modelMapper, times(1)).map(fournisseurDto, Fournisseur.class);
//        verify(fournisseurRepository, times(1)).save(fournisseur);
//        verify(modelMapper, times(1)).map(fournisseur, FournisseurDto.class);
    }

    @Test
    void updateFournisseurTest() {
        // Mock the behavior of the repository
        FournisseurDto fournisseurDto = new FournisseurDto();
        Fournisseur existingFournisseur = new Fournisseur();
        when(fournisseurRepository.findById(fournisseurDto.getId())).thenReturn(Optional.of(existingFournisseur));
        when(fournisseurRepository.save(existingFournisseur)).thenReturn(existingFournisseur);

        // Call the method to be tested
        FournisseurDto result = fournisseurService.updateFournisseur(fournisseurDto);

        // Verify the interactions and assertions
        assertNotNull(result);
        verify(fournisseurRepository, times(1)).findById(fournisseurDto.getId());
        verify(modelMapper, times(1)).map(fournisseurDto, existingFournisseur);
        verify(fournisseurRepository, times(1)).save(existingFournisseur);
        verify(modelMapper, times(1)).map(existingFournisseur, FournisseurDto.class);
    }

    @Test
    void deleteFournisseurTest() {
        // Call the method to be tested
        fournisseurService.deleteFournisseur(1L);

        // Verify the interactions
        verify(fournisseurRepository, times(1)).deleteById(1L);
    }
}
