package com.example.LabTech;


import com.example.LabTech.entite.Analyse;
import com.example.LabTech.service.AnalyseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnalyseServiceTestAnalyse {

    @Autowired
    private AnalyseService analyseService;

    @Test
    public void testAddAnalyse() {
        // Arrange
        Analyse analyseToAdd = createSampleAnalyse();

        // Act
        Analyse addedAnalyse = analyseService.addAnalyse(analyseToAdd);

        // Assert
        assertNotNull(addedAnalyse, "Added Analyse should not be null");
        assertEquals(analyseToAdd.getDate_debut(), addedAnalyse.getDate_debut(), "Date_debut should match");
        // Add more assertions as needed
    }

    @Test
    public void testFindAnalyseById() {
        // Arrange
        Analyse analyseToAdd = createSampleAnalyse();
        Analyse addedAnalyse = analyseService.addAnalyse(analyseToAdd);

        // Act
        Optional<Analyse> foundAnalyse = analyseService.getAnalyseById(addedAnalyse.getId());

        // Assert
        assertTrue(foundAnalyse.isPresent(), "Analyse should be present");
        assertEquals(addedAnalyse.getId(), foundAnalyse.get().getId(), "Found Analyse ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateAnalyse() {
        // Arrange
        Analyse analyseToAdd = createSampleAnalyse();
        Analyse addedAnalyse = analyseService.addAnalyse(analyseToAdd);

        // Act
        // Modify attributes of addedAnalyse
        addedAnalyse.setDate_debut(new Date());
        Analyse updatedAnalyse = analyseService.updateAnalyse(addedAnalyse);

        // Assert
        assertNotNull(updatedAnalyse, "Updated Analyse should not be null");
        assertEquals(addedAnalyse.getDate_debut(), updatedAnalyse.getDate_debut(), "Date_debut should be updated");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteAnalyse() {
        // Arrange
        Analyse analyseToAdd = createSampleAnalyse();
        Analyse addedAnalyse = analyseService.addAnalyse(analyseToAdd);

        // Act
        analyseService.deleteAnalyse(addedAnalyse.getId());

        // Assert
        Optional<Analyse> deletedAnalyse = analyseService.getAnalyseById(addedAnalyse.getId());
        assertFalse(deletedAnalyse.isPresent(), "Analyse should be deleted");
    }

    @Test
    public void testGetAllAnalyses() {
        // Arrange
        Analyse analyse1 = createSampleAnalyse();
        Analyse analyse2 = createSampleAnalyse();
        analyseService.addAnalyse(analyse1);
        analyseService.addAnalyse(analyse2);

        // Act
        List<Analyse> allAnalyses = analyseService.getAllAnalyses();

        // Assert
        assertNotNull(allAnalyses, "List of Analyses should not be null");
       // assertEquals(2, allAnalyses.size(), "List size should match the expected size");
        // Add more assertions as needed
    }

    private Analyse createSampleAnalyse() {
        Analyse analyse = new Analyse();
        // Set attributes as needed
        analyse.setDate_debut(new Date());
        return analyse;
    }
}
