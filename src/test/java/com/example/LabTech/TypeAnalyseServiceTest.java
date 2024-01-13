package com.example.LabTech;

import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.service.TypeAnalyseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TypeAnalyseServiceTest {

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @Test
    public void testAddTypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyseToAdd = createSampleTypeAnalyse();

        // Act
        Type_Analyse addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseToAdd);

        // Assert
        assertNotNull(addedTypeAnalyse, "Added TypeAnalyse should not be null");
        // Add more assertions as needed
    }

    @Test
    public void testFindTypeAnalyseById() {


        // Act
        Optional<Type_Analyse> foundTypeAnalyse = typeAnalyseService.gettypeAnalyseById(1);

        // Assert
        assertTrue(foundTypeAnalyse.isPresent(), "TypeAnalyse should be present");
        assertEquals(1, foundTypeAnalyse.get().getId(), "Found TypeAnalyse ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateTypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyseToAdd = createSampleTypeAnalyse();
        Type_Analyse addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseToAdd);


        Type_Analyse updatedTypeAnalyse = typeAnalyseService.updatetypeAnalyse(addedTypeAnalyse);

        // Assert
        assertNotNull(updatedTypeAnalyse, "Updated TypeAnalyse should not be null");

    }

    @Test
    public void testDeleteTypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyseToAdd = createSampleTypeAnalyse();
        Type_Analyse addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseToAdd);

        // Act
        typeAnalyseService.deletetypeAnalyse(addedTypeAnalyse.getId());

        // Assert
        Optional<Type_Analyse> deletedTypeAnalyse = typeAnalyseService.gettypeAnalyseById(addedTypeAnalyse.getId());
        assertFalse(deletedTypeAnalyse.isPresent(), "TypeAnalyse should be deleted");
    }

    @Test
    public void testGetAllTypeAnalyses() {
        // Arrange
        Type_Analyse typeAnalyse1 = createSampleTypeAnalyse();
        Type_Analyse typeAnalyse2 = createSampleTypeAnalyse();
        typeAnalyseService.addtypeAnalyse(typeAnalyse1);
        typeAnalyseService.addtypeAnalyse(typeAnalyse2);

        // Act
        List<Type_Analyse> allTypeAnalyses = typeAnalyseService.getAllttypeAnalyse();

        // Assert
        assertNotNull(allTypeAnalyses, "List of TypeAnalyses should not be null");

        // Add more assertions as needed
    }

    private Type_Analyse createSampleTypeAnalyse() {
        Type_Analyse typeAnalyse = new Type_Analyse();
        // Set attributes as needed
        return typeAnalyse;
    }
}

