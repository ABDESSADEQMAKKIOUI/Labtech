package com.example.LabTech;

import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.service.EchantillonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EchantillonServiceTestAnalyse {

    @Autowired
    private EchantillonService echantillonService;

    public Echantillon createSampleEchantillon(){
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("John");
        patient.setPrenom("Doe");
        patient.setAdress("123 Main St");
        patient.setEmail("john.doe@example.com");
        patient.setTele("123-456-7890");

        Echantillon echantillon = new Echantillon();
        echantillon.setId(1L);
        echantillon.setPatient(patient);
        echantillon.setDate_prend(new Date());
        return  echantillon ;

    }

    @Test
    public void testAddEchantillon() {
        // Arrange
        Echantillon echantillonToAdd = createSampleEchantillon();

        // Act
        Echantillon addedEchantillon = echantillonService.addEchantillon(echantillonToAdd);

        // Assert
        assertNotNull(addedEchantillon, "Added Echantillon should not be null");
        assertEquals(echantillonToAdd.getPatient().getId(), addedEchantillon.getPatient().getId(), "Patient IDs should match");

    }

    @Test
    public void testFindEchantillonById() {


        Optional<Echantillon> foundEchantillon = echantillonService.getEchantillonById(1L);

        assertTrue(foundEchantillon.isPresent(), "Echantillon should be present");
        assertEquals(1L, foundEchantillon.get().getId(), "Found Echantillon ID should match the expected ID");
        // Add more assertions as needed
    }

    @Test
    public void testUpdateEchantillon() {
        // Arrange
        Echantillon echantillonToAdd = createSampleEchantillon();
        Echantillon addedEchantillon = echantillonService.addEchantillon(echantillonToAdd);

        // Act
        // Modify attributes of addedEchantillon
        addedEchantillon.setDate_prend(new Date(2002/03/12));
        Echantillon updatedEchantillon = echantillonService.updateEchantillon(addedEchantillon);

        // Assert
        assertNotNull(updatedEchantillon, "Updated Echantillon should not be null");
        assertEquals(2002/03/12, updatedEchantillon.getDate_prend(), "Attribute should be updated");
        // Add more assertions as needed
    }
    @Test
    public void testGetAllEchantillons() {
        // Arrange
        Echantillon echantillon1 = createSampleEchantillon();
        Echantillon echantillon2 = createSampleEchantillon();
        echantillonService.addEchantillon(echantillon1);
        echantillonService.addEchantillon(echantillon2);

        // Act
        List<Echantillon> allEchantillons = echantillonService.getAllEchantillons();

        // Assert
        assertNotNull(allEchantillons, "List of Echantillons should not be null");
        assertEquals(2, allEchantillons.size(), "List size should match the expected size");
        // Add more assertions as needed
    }

    @Test
    public void testDeleteEchantillon()  {

        Echantillon echantillonToAdd = createSampleEchantillon();
        Echantillon addedEchantillon = echantillonService.addEchantillon(echantillonToAdd);
        echantillonService.deleteEchantillon(addedEchantillon.getId());
        Optional<Echantillon> deletedEchantillon = echantillonService.getEchantillonById(addedEchantillon.getId());
        assertFalse(((Optional<?>) deletedEchantillon).isPresent(), "Echantillon should be deleted");
    }
}

