package com.example.LabTech;

import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.entite.Reactif;
import com.example.LabTech.service.ReactifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReactifServiceTestAnalyse {

    @Autowired
    private ReactifService reactifService;

    @Test
    public void testAddReactif() {
        // Arrange
        Reactif reactifToAdd = createSampleReactif();

        // Act
        Reactif addedReactif = reactifService.addReactif(reactifToAdd);

        // Assert
        assertNotNull(addedReactif, "Added Reactif should not be null");
        assertEquals(reactifToAdd.getNom(), addedReactif.getNom(), "Nom should match");

    }

    @Test
    public void testFindReactifById() {
        // Arrange
        Reactif reactifToAdd = createSampleReactif();
        Reactif addedReactif = reactifService.addReactif(reactifToAdd);

        // Act
        Optional<Reactif> foundReactif = reactifService.getReactifById(addedReactif.getId());

        // Assert
        assertTrue(foundReactif.isPresent(), "Reactif should be present");
        assertEquals(addedReactif.getId(), foundReactif.get().getId(), "Found Reactif ID should match the expected ID");

    }

    @Test
    public void testUpdateReactif() {
        // Arrange
        Reactif reactifToAdd = createSampleReactif();
        Reactif addedReactif = reactifService.addReactif(reactifToAdd);

        // Act
        // Modify attributes of addedReactif
        addedReactif.setNom("UpdatedNom");
        Reactif updatedReactif = reactifService.updateReactif(addedReactif);

        // Assert
        assertNotNull(updatedReactif, "Updated Reactif should not be null");
        assertEquals("UpdatedNom", updatedReactif.getNom(), "Nom should be updated");

    }

    @Test
    public void testDeleteReactif() {
        // Arrange
        Reactif reactifToAdd = createSampleReactif();
        Reactif addedReactif = reactifService.addReactif(reactifToAdd);

        // Act
        reactifService.deleteReactif(addedReactif.getId());

        // Assert
        Optional<Reactif> deletedReactif = reactifService.getReactifById(addedReactif.getId());
        assertFalse(deletedReactif.isPresent(), "Reactif should be deleted");
    }

    @Test
    public void testGetAllReactifs() {
        // Arrange
        Reactif reactif1 = createSampleReactif();
        Reactif reactif2 = createSampleReactif();
        reactifService.addReactif(reactif1);
        reactifService.addReactif(reactif2);

        // Act
        List<Reactif> allReactifs = reactifService.getAllReactifs();

        // Assert
        assertNotNull(allReactifs, "List of Reactifs should not be null");
        assertEquals(2, allReactifs.size(), "List size should match the expected size");

    }

    private Reactif createSampleReactif() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1L);
        fournisseur.setNom("Supplier ABC");

        Reactif reactif = new Reactif();
        reactif.setId(1L);
        reactif.setQuantity(100);
        reactif.setNom("Reagent XYZ");
        reactif.setDate_expiration(new Date());
        reactif.setDescription("Description of the reagent");
        reactif.setFournisseur(fournisseur);

        return reactif;
    }
}


