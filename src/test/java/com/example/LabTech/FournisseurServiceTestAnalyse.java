package com.example.LabTech;

import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.service.FournisseurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FournisseurServiceTestAnalyse {

    @Autowired
    private FournisseurService fournisseurService;
    public Fournisseur createSampleFournisseur(){
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1L);
        fournisseur.setNom("Supplier ABC");
       return fournisseur;
    }
    @Test
    public void testAddFournisseur() {
        // Arrange
        Fournisseur fournisseurToAdd = createSampleFournisseur();

        // Act
        Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseurToAdd);

        // Assert
        assertNotNull(addedFournisseur, "Added Fournisseur should not be null");
        assertEquals(fournisseurToAdd.getNom(), addedFournisseur.getNom(), "Nom should match");

    }

    @Test
    public void testFindFournisseurById() {
        // Arrange
        Fournisseur fournisseurToAdd = createSampleFournisseur();
        Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseurToAdd);

        // Act
        Optional<Fournisseur> foundFournisseur = fournisseurService.getFournisseurById(addedFournisseur.getId());

        // Assert
        assertTrue(foundFournisseur.isPresent(), "Fournisseur should be present");
        assertEquals(addedFournisseur.getId(), foundFournisseur.get().getId(), "Found Fournisseur ID should match the expected ID");
        // Add more assertions as needed

    }
        @Test
    public void testUpdateFournisseur() {
            // Arrange
            Fournisseur fournisseurToAdd = createSampleFournisseur();
            Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseurToAdd);

            // Act
            // Modify attributes of addedFournisseur
            addedFournisseur.setNom("UpdatedNom");
            Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(addedFournisseur);

            // Assert
            assertNotNull(updatedFournisseur, "Updated Fournisseur should not be null");
            assertEquals("UpdatedNom", updatedFournisseur.getNom(), "Nom should be updated");
            // Add more assertions as needed
        }
        @Test
        public void testGetAllFournisseurs() {
            // Arrange
            Fournisseur fournisseur1 = createSampleFournisseur();
            Fournisseur fournisseur2 = createSampleFournisseur();
            fournisseurService.addFournisseur(fournisseur1);
            fournisseurService.addFournisseur(fournisseur2);

            // Act
            List<Fournisseur> allFournisseurs = fournisseurService.getAllFournisseurs();

            // Assert
            assertNotNull(allFournisseurs, "List of Fournisseurs should not be null");
            assertEquals(2, allFournisseurs.size(), "List size should match the expected size");
            // Add more assertions as needed
        }

    @Test
    public void testDeleteFournisseur() {
            // Arrange
            Fournisseur fournisseurToAdd = createSampleFournisseur();
            Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseurToAdd);

            // Act
            fournisseurService.deleteFournisseur(addedFournisseur.getId());

            // Assert
            Optional<Fournisseur> deletedFournisseur = fournisseurService.getFournisseurById(addedFournisseur.getId());
            assertFalse(deletedFournisseur.isPresent(), "Fournisseur should be deleted");
        }
}

