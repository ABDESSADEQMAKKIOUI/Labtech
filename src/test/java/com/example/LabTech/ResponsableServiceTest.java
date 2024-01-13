package com.example.LabTech;

import com.example.LabTech.entite.Responsable;
import com.example.LabTech.service.ResponsableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResponsableServiceTest {

    @Autowired
    private ResponsableService responsableService;

    @Test
    public void testAddResponsable() {
        // Arrange
        Responsable responsableToAdd = createSampleResponsable();

        // Act
        Responsable addedResponsable = responsableService.addResponsable(responsableToAdd);

        // Assert
        assertNotNull(addedResponsable, "Added Responsable should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testFindResponsableById() {
        // Arrange
        Responsable responsableToAdd = createSampleResponsable();
        Responsable addedResponsable = responsableService.addResponsable(responsableToAdd);

        // Act
        Optional<Responsable> foundResponsable = responsableService.getResponsableById(addedResponsable.getId());

        // Assert
        assertTrue(foundResponsable.isPresent(), "Responsable should be present");
        assertEquals(addedResponsable.getId(), foundResponsable.get().getId(), "Found Responsable ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateResponsable() {
        // Arrange
        Responsable responsableToAdd = createSampleResponsable();
        Responsable addedResponsable = responsableService.addResponsable(responsableToAdd);

        // Act
        // Modify attributes of addedResponsable
        // ...

        // Update the Responsable in the service
        Responsable updatedResponsable = responsableService.updateResponsable(addedResponsable);

        // Assert
        assertNotNull(updatedResponsable, "Updated Responsable should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteResponsable() {
        // Arrange
        Responsable responsableToAdd = createSampleResponsable();
        Responsable addedResponsable = responsableService.addResponsable(responsableToAdd);

        // Act
        responsableService.deleteResponsable(addedResponsable.getId());

        // Assert
        Optional<Responsable> deletedResponsable = responsableService.getResponsableById(addedResponsable.getId());
        assertFalse(deletedResponsable.isPresent(), "Responsable should be deleted");
    }

    @Test
    public void testGetAllResponsables() {
        // Arrange
        Responsable responsable1 = createSampleResponsable();
        Responsable responsable2 = createSampleResponsable();
        responsableService.addResponsable(responsable1);
        responsableService.addResponsable(responsable2);

        // Act
        List<Responsable> allResponsables = responsableService.getAllresponsable();

        // Assert
        assertNotNull(allResponsables, "List of Responsables should not be null");
        // Add more assertions as needed
    }

    private Responsable createSampleResponsable() {
        Responsable responsable = new Responsable();
        // Set attributes as needed
        return responsable;
    }
}

