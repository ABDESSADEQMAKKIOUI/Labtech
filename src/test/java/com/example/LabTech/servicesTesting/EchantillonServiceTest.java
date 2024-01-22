package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.repository.EchantillonRepository;
import com.example.LabTech.service.EchantillonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EchantillonServiceTest {

    @Autowired
    private EchantillonRepository echantillonRepository;

    @Autowired
    private EchantillonService echantillonService;

    @Test
    void getAllEchantillonsTest() {
        // Ajouter des échantillons fictifs à la base de données
        Echantillon echantillon1 = new Echantillon();
        Echantillon echantillon2 = new Echantillon();
        echantillonRepository.saveAll(Arrays.asList(echantillon1, echantillon2));

        // Appeler la méthode à tester
        List<EchantillonDto> result = echantillonService.getAllEchantillons();

        // Vérifier les assertions
        assertNotNull(result);
    }

    @Test
    void getEchantillonByIdTest() {
        // Ajouter un échantillon fictif à la base de données
        Echantillon echantillon = new Echantillon();
        echantillonRepository.save(echantillon);

        // Appeler la méthode à tester
        Optional<EchantillonDto> result = echantillonService.getEchantillonById(echantillon.getId());

        // Vérifier les assertions
        assertTrue(result.isPresent());
    }

    @Test
    void addEchantillonTest() {
        // Créer des objets EchantillonDto
        EchantillonDto echantillon1 = new EchantillonDto();
        echantillon1.setDate_prend(new Date());

        EchantillonDto echantillon2 = new EchantillonDto();
        echantillon2.setPatientId(2L);
        echantillon2.setDate_prend(new Date());

        EchantillonDto echantillon3 = new EchantillonDto();
        echantillon3.setPatientId(3L);
        echantillon3.setDate_prend(new Date());

        EchantillonDto echantillon6 = new EchantillonDto();
        echantillon6.setPatientId(6L);
        echantillon6.setDate_prend(new Date());

        // Appeler la méthode à tester
        echantillonService.addEchantillon(echantillon1);
        echantillonService.addEchantillon(echantillon2);
        echantillonService.addEchantillon(echantillon3);
        echantillonService.addEchantillon(echantillon6);

        // Vérifier que les échantillons ont été ajoutés à la base de données
        List<Echantillon> echantillons = echantillonRepository.findAll();
        assertEquals(4, echantillons.size());
    }

    @Test
    void updateEchantillonTest() {
        // Ajouter un échantillon fictif à la base de données
        Echantillon echantillon = new Echantillon();
        echantillonRepository.save(echantillon);

        // Créer un objet EchantillonDto avec les modifications
        EchantillonDto echantillonDto = new EchantillonDto();
        echantillonDto.setId(echantillon.getId());
        echantillonDto.setDate_prend(new Date());

        // Appeler la méthode à tester
        EchantillonDto result = echantillonService.updateEchantillon(echantillonDto);

        // Vérifier les assertions
        assertNotNull(result);
        assertEquals(echantillonDto.getDate_prend(), result.getDate_prend());
    }

    @Test
    void deleteEchantillonTest() {
        // Ajouter un échantillon fictif à la base de données
        Echantillon echantillon = new Echantillon();
        echantillonRepository.save(echantillon);

        // Appeler la méthode à tester
        echantillonService.deleteEchantillon(echantillon.getId());

        // Vérifier que l'échantillon a été supprimé de la base de données
        assertEquals(0, echantillonRepository.count());
    }
}
