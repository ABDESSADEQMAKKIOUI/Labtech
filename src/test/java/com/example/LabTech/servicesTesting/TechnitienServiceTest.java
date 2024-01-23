package com.example.LabTech.servicesTesting;

import com.example.LabTech.Annotation.MyTag;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.repository.TechnitienRepository;
import com.example.LabTech.service.TechnitientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TechnitienServiceTest {

    @Autowired
    private TechnitienRepository technitienRepository;

    @Autowired
    private TechnitientService technitienService;

    @MyTag
    void getAllTechnitiens() {
        // Ajouter des techniciens fictifs à la base de données
        Technitien technitien1 = new Technitien();
        Technitien technitien2 = new Technitien();
        technitienRepository.save(technitien1);
        technitienRepository.save(technitien2);

        // Appeler la méthode à tester
        List<TechnitienDto> result = technitienService.getAlltechnitiens();

        // Vérifier les assertions
        assertNotNull(result);
    }

    @MyTag
    void getTechnitienById() {
        // Ajouter un technicien fictif à la base de données
        Technitien technitien = new Technitien();
        technitienRepository.save(technitien);

        // Appeler la méthode à tester
        Optional<TechnitienDto> result = technitienService.gettechnitienById(technitien.getId());

        // Vérifier les assertions
        assertEquals(true, result.isPresent());
    }

    @Test
    void addTechnitien() {
        // Créer des objets TechnitienDto
        TechnitienDto technitien1 = new TechnitienDto();
        technitien1.setNom("Smith");
        technitien1.setPrenom("John");
        technitien1.setEmail("john.smith@example.com");

        TechnitienDto technitien2 = new TechnitienDto();
        technitien2.setNom("Johnson");
        technitien2.setPrenom("Alice");
        technitien2.setEmail("alice.johnson@example.com");

        // Appeler la méthode à tester
        technitienService.addtechnitien(technitien1);
        technitienService.addtechnitien(technitien2);

        // Vérifier que les techniciens ont été ajoutés à la base de données
        List<Technitien> techniciens = technitienRepository.findAll();
        assertEquals(2, techniciens.size());
    }

    @MyTag
    void updateTechnitien() {
        // Ajouter un technicien fictif à la base de données
        Technitien technitien = new Technitien();
        technitienRepository.save(technitien);

        // Créer un objet TechnitienDto avec les modifications
        TechnitienDto technitienDto = new TechnitienDto();
        technitienDto.setId(technitien.getId());
        technitienDto.setNom("Doe");

        // Appeler la méthode à tester
        TechnitienDto result = technitienService.updatetechnitien(technitienDto);

        // Vérifier les assertions
        assertEquals(technitienDto.getNom(), result.getNom());
    }

    @MyTag
    void deleteTechnitien() {
        // Ajouter un technicien fictif à la base de données
        Technitien technitien = new Technitien();
        technitienRepository.save(technitien);

        // Appeler la méthode à tester
        technitienService.deletetechnitien(technitien.getId());

        // Vérifier que le technicien a été supprimé de la base de données

    }
}
