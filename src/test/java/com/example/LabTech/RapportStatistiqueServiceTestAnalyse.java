package com.example.LabTech;

import com.example.LabTech.entite.RapportStatistique;
import com.example.LabTech.entite.enums.Periode_Rapport;
import com.example.LabTech.entite.enums.Type_Rapport;
import com.example.LabTech.service.RapportStatistiqueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RapportStatistiqueServiceTestAnalyse {

    @Autowired
    private RapportStatistiqueService rapportStatistiqueService;

    @Test
    public void testAddRapportStatistique() {
        // Arrange
        RapportStatistique rapportStatistiqueToAdd = createSampleRapportStatistique();

        // Act
        RapportStatistique addedRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistiqueToAdd);

        // Assert
        assertNotNull(addedRapportStatistique, "Added RapportStatistique should not be null");
        assertEquals(rapportStatistiqueToAdd.getTypeRapport(), addedRapportStatistique.getTypeRapport(), "TypeRapport should match");
        // Add more assertions as needed
    }

    @Test
    public void testFindRapportStatistiqueById() {
        // Arrange
        RapportStatistique rapportStatistiqueToAdd = createSampleRapportStatistique();
        RapportStatistique addedRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistiqueToAdd);

        // Act
        Optional<RapportStatistique> foundRapportStatistique = rapportStatistiqueService.getRapportStatistiqueById(addedRapportStatistique.getId());

        // Assert
        assertTrue(foundRapportStatistique.isPresent(), "RapportStatistique should be present");
        assertEquals(addedRapportStatistique.getId(), foundRapportStatistique.get().getId(), "Found RapportStatistique ID should match the expected ID");

    }

    @Test
    public void testUpdateRapportStatistique() {
        // Arrange
        RapportStatistique rapportStatistiqueToAdd = createSampleRapportStatistique();
        RapportStatistique addedRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistiqueToAdd);

        // Act
        // Modify attributes of addedRapportStatistique
        addedRapportStatistique.setTypeRapport(Type_Rapport.gestion_de_stoke);
        RapportStatistique updatedRapportStatistique = rapportStatistiqueService.updateRapportStatistique(addedRapportStatistique);

        // Assert
        assertNotNull(updatedRapportStatistique, "Updated RapportStatistique should not be null");
        assertEquals("UpdatedType", updatedRapportStatistique.getTypeRapport(), "TypeRapport should be updated");

    }

    @Test
    public void testDeleteRapportStatistique() {
        // Arrange
        RapportStatistique rapportStatistiqueToAdd = createSampleRapportStatistique();
        RapportStatistique addedRapportStatistique = rapportStatistiqueService.addRapportStatistique(rapportStatistiqueToAdd);

        // Act
        rapportStatistiqueService.deleteRapportStatistique(addedRapportStatistique.getId());

        // Assert
        Optional<RapportStatistique> deletedRapportStatistique = rapportStatistiqueService.getRapportStatistiqueById(addedRapportStatistique.getId());
        assertFalse(deletedRapportStatistique.isPresent(), "RapportStatistique should be deleted");
    }

    @Test
    public void testGetAllRapportStatistiques() {
        // Arrange
        RapportStatistique rapportStatistique1 = createSampleRapportStatistique();
        RapportStatistique rapportStatistique2 = createSampleRapportStatistique();
        rapportStatistiqueService.addRapportStatistique(rapportStatistique1);
        rapportStatistiqueService.addRapportStatistique(rapportStatistique2);

        // Act
        List<RapportStatistique> allRapportStatistiques = rapportStatistiqueService.getAllRapportStatistiques();

        // Assert
        assertNotNull(allRapportStatistiques, "List of RapportStatistiques should not be null");
        assertEquals(2, allRapportStatistiques.size(), "List size should match the expected size");

    }

    private RapportStatistique createSampleRapportStatistique() {
        RapportStatistique rapportStatistique = new RapportStatistique();
        rapportStatistique.setId(1L);
        rapportStatistique.setTypeRapport(Type_Rapport.tendances);
        rapportStatistique.setPeriodeRapport(Periode_Rapport.par_mois);

        return rapportStatistique;
    }
}


