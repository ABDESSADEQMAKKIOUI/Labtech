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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class FournisseurServiceTest {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FournisseurService fournisseurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFournisseursTest() {
        // Appeler la méthode à tester
        List<FournisseurDto> result = fournisseurService.getAllFournisseurs();

        // Vérifier les assertions
        assertNotNull(result);

    }

    @Test
    void getFournisseurByIdTest() {
        // Ajouter un fournisseur fictif à la base de données
        Fournisseur fournisseur = new Fournisseur();
        fournisseurRepository.save(fournisseur);

        // Appeler la méthode à tester
        Optional<FournisseurDto> result = fournisseurService.getFournisseurById(fournisseur.getId());

        // Vérifier les assertions
        assertTrue(result.isPresent());
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
        // Ajouter un fournisseur fictif à la base de données
        Fournisseur fournisseur = new Fournisseur();
        fournisseurRepository.save(fournisseur);

        // Créer un objet FournisseurDto avec les modifications
        FournisseurDto fournisseurDto = new FournisseurDto();
        fournisseurDto.setId(fournisseur.getId());
        fournisseurDto.setNom("Fournisseur Modifié");

        // Appeler la méthode à tester
        FournisseurDto result = fournisseurService.updateFournisseur(fournisseurDto);

        // Vérifier les assertions
        assertNotNull(result);
        assertEquals(fournisseurDto.getNom(), result.getNom());
    }

    @Test
    void deleteFournisseurTest() {
        // Ajouter un fournisseur fictif à la base de données
        Fournisseur fournisseur = new Fournisseur();
        fournisseurRepository.save(fournisseur);

        // Appeler la méthode à tester
        fournisseurService.deleteFournisseur(fournisseur.getId());

        // Vérifier que le fournisseur a été supprimé de la base de données
        assertFalse(fournisseurRepository.findById(fournisseur.getId()).isPresent());
    }
}
