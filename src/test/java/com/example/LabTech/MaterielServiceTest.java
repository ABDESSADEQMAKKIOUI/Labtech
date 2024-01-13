package com.example.LabTech;

import com.example.LabTech.entite.Materiel;
import com.example.LabTech.service.MaterielService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MaterielServiceTest {

    @Autowired
    private MaterielService materielService;

    @Test
    public void testAddMateriel() {
        // Arrange
        Materiel materielToAdd = createSampleMateriel();

        // Act
        Materiel addedMateriel = materielService.addMateriel(materielToAdd);

        // Assert
        assertNotNull(addedMateriel, "Added Materiel should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testFindMaterielById() {
        // Arrange
        Materiel materielToAdd = createSampleMateriel();
        Materiel addedMateriel = materielService.addMateriel(materielToAdd);

        // Act
        Optional<Materiel> foundMateriel = materielService.getMaterielById(addedMateriel.getId());

        // Assert
        assertTrue(foundMateriel.isPresent(), "Materiel should be present");
        assertEquals(addedMateriel.getId(), foundMateriel.get().getId(), "Found Materiel ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateMateriel() {
        // Arrange
        Materiel materielToAdd = createSampleMateriel();
        Materiel addedMateriel = materielService.addMateriel(materielToAdd);

        // Act
        // Modify attributes of addedMateriel
        // ...

        // Update the materiel in the service
        Materiel updatedMateriel = materielService.updateMateriel(addedMateriel);

        // Assert
        assertNotNull(updatedMateriel, "Updated Materiel should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteMateriel() {
        // Arrange
        Materiel materielToAdd = createSampleMateriel();
        Materiel addedMateriel = materielService.addMateriel(materielToAdd);

        // Act
        materielService.deleteMateriel(addedMateriel.getId());

        // Assert
        Optional<Materiel> deletedMateriel = materielService.getMaterielById(addedMateriel.getId());
        assertFalse(deletedMateriel.isPresent(), "Materiel should be deleted");
    }

    @Test
    public void testGetAllMateriels() {
        // Arrange
        Materiel materiel1 = createSampleMateriel();
        Materiel materiel2 = createSampleMateriel();
        materielService.addMateriel(materiel1);
        materielService.addMateriel(materiel2);

        // Act
        List<Materiel> allMateriels = materielService.getAllMateriel();

        // Assert
        assertNotNull(allMateriels, "List of Materiels should not be null");
        // Add more assertions as needed
    }

    private Materiel createSampleMateriel() {
        Materiel materiel = new Materiel();
        // Set attributes as needed
        return materiel;
    }
}
