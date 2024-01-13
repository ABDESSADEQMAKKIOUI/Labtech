package com.example.LabTech;

import com.example.LabTech.entite.Technitien;
import com.example.LabTech.service.TechnitientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TechnitienServiceTest {

    @Autowired
    private TechnitientService technitienService;

    @Test
    public void testAddTechnitien() {
        // Arrange
        Technitien technitienToAdd = createSampleTechnitien();

        // Act
        Technitien addedTechnitien = technitienService.addtechnitien(technitienToAdd);

        // Assert
        assertNotNull(addedTechnitien, "Added Technitien should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testFindTechnitienById() {
        // Arrange
        Technitien technitienToAdd = createSampleTechnitien();
        Technitien addedTechnitien = technitienService.addtechnitien(technitienToAdd);

        // Act
        Optional<Technitien> foundTechnitien = technitienService.gettechnitienById(addedTechnitien.getId());

        // Assert
        assertTrue(foundTechnitien.isPresent(), "Technitien should be present");
        assertEquals(addedTechnitien.getId(), foundTechnitien.get().getId(), "Found Technitien ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateTechnitien() {
        // Arrange
        Technitien technitienToAdd = createSampleTechnitien();
        Technitien addedTechnitien = technitienService.addtechnitien(technitienToAdd);

        // Act
        // Modify attributes of addedTechnitien
        // ...

        // Update the Technitien in the service
        Technitien updatedTechnitien = technitienService.updatetechnitien(addedTechnitien);

        // Assert
        assertNotNull(updatedTechnitien, "Updated Technitien should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteTechnitien() {
        // Arrange
        Technitien technitienToAdd = createSampleTechnitien();
        Technitien addedTechnitien = technitienService.addtechnitien(technitienToAdd);

        // Act
        technitienService.deletetechnitien(addedTechnitien.getId());

        // Assert
        Optional<Technitien> deletedTechnitien = technitienService.gettechnitienById(addedTechnitien.getId());
        assertFalse(deletedTechnitien.isPresent(), "Technitien should be deleted");
    }

    @Test
    public void testGetAllTechnitiens() {
        // Arrange
        Technitien technitien1 = createSampleTechnitien();
        Technitien technitien2 = createSampleTechnitien();
        technitienService.addtechnitien(technitien1);
        technitienService.addtechnitien(technitien2);

        // Act
        List<Technitien> allTechnitiens = technitienService.getAlltechnitiens();

        // Assert
        assertNotNull(allTechnitiens, "List of Technitiens should not be null");
        // Add more assertions as needed
    }

    private Technitien createSampleTechnitien() {
        Technitien technitien = new Technitien();
        // Set attributes as needed
        return technitien;
    }
}
