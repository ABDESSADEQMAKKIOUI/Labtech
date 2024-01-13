package com.example.LabTech;

import com.example.LabTech.entite.Enorm;
import com.example.LabTech.service.EnormService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnormServiceTest {

    @Autowired
    private EnormService enormService;

    @Test
    public void testAddEnorm() {
        // Arrange
        Enorm enormToAdd = createSampleEnorm();

        // Act
        Enorm addedEnorm = enormService.addEnorms(enormToAdd);

        // Assert
        assertNotNull(addedEnorm, "Added Enorm should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testFindEnormById() {
        // Arrange
        Enorm enormToAdd = createSampleEnorm();
        Enorm addedEnorm = enormService.addEnorms(enormToAdd);

        // Act
        Optional<Enorm> foundEnorm = enormService.getEnormsById(addedEnorm.getId());

        // Assert
        assertTrue(foundEnorm.isPresent(), "Enorm should be present");
        assertEquals(addedEnorm.getId(), foundEnorm.get().getId(), "Found Enorm ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateEnorm() {
        // Arrange
        Enorm enormToAdd = createSampleEnorm();
        Enorm addedEnorm = enormService.addEnorms(enormToAdd);

        // Act
        // Modify attributes of addedEnorm
        // ...

        // Update the Enorm in the service
        Enorm updatedEnorm = enormService.updateEnorms(addedEnorm);

        // Assert
        assertNotNull(updatedEnorm, "Updated Enorm should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteEnorm() {
        // Arrange
        Enorm enormToAdd = createSampleEnorm();
        Enorm addedEnorm = enormService.addEnorms(enormToAdd);

        // Act
        enormService.deleteEnorms(addedEnorm.getId());

        // Assert
        Optional<Enorm> deletedEnorm = enormService.getEnormsById(addedEnorm.getId());
        assertFalse(deletedEnorm.isPresent(), "Enorm should be deleted");
    }

    @Test
    public void testGetAllEnorms() {
        // Arrange
        Enorm enorm1 = createSampleEnorm();
        Enorm enorm2 = createSampleEnorm();
        enormService.addEnorms(enorm1);
        enormService.addEnorms(enorm2);

        // Act
        List<Enorm> allEnorms = enormService.getAllEnorms();

        // Assert
        assertNotNull(allEnorms, "List of Enorms should not be null");
        // Add more assertions as needed
    }

    private Enorm createSampleEnorm() {
        Enorm enorm = new Enorm();
        // Set attributes as needed
        return enorm;
    }
}

