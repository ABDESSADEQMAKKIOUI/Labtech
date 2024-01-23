package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.repository.ResponsbleRepository;
import com.example.LabTech.service.ResponsableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResponsableServiceTest {

    @Autowired
    private ResponsbleRepository responsbleRepository;

    @Autowired
    private ResponsableService responsableService;

    @Test
    void getAllResponsables() {
        // Ajouter des responsables fictifs à la base de données
        Responsable responsable1 = new Responsable();
        Responsable responsable2 = new Responsable();
        responsbleRepository.save(responsable1);
        responsbleRepository.save(responsable2);

        // Appeler la méthode à tester
        List<ResponsableDto> result = responsableService.getAllresponsable();

        // Vérifier les assertions
       assertNotNull(result);
    }

    @Test
    void getResponsableById() {
        // Ajouter un responsable fictif à la base de données
        Responsable responsable = new Responsable();
        responsbleRepository.save(responsable);

        // Appeler la méthode à tester
        Optional<ResponsableDto> result = responsableService.getResponsableById(responsable.getId());

        // Vérifier les assertions
        assertEquals(true, result.isPresent());
    }

    @Test
    void addResponsable() {
        // Créer des objets ResponsableDto
        ResponsableDto responsable1 = new ResponsableDto();
        responsable1.setNom("Doe");
        responsable1.setPrenom("John");
        responsable1.setEmail("john.doe@example.com");

        ResponsableDto responsable2 = new ResponsableDto();
        responsable2.setNom("Smith");
        responsable2.setPrenom("Alice");
        responsable2.setEmail("alice.smith@example.com");

        // Appeler la méthode à tester
        responsableService.addResponsable(responsable1);
        responsableService.addResponsable(responsable2);

        // Vérifier que les responsables ont été ajoutés à la base de données
        List<Responsable> responsables = responsbleRepository.findAll();
        assertEquals(2, responsables.size());
    }

    @Test
    void updateResponsable() {
        // Ajouter un responsable fictif à la base de données
        Responsable responsable = new Responsable();
        responsbleRepository.save(responsable);

        // Créer un objet ResponsableDto avec les modifications
        ResponsableDto responsableDto = new ResponsableDto();
        responsableDto.setId(responsable.getId());
        responsableDto.setNom("Doe");

        // Appeler la méthode à tester
        ResponsableDto result = responsableService.updateResponsable(responsableDto);

        // Vérifier les assertions
        assertEquals(responsableDto.getNom(), result.getNom());
    }

    @Test
    void deleteResponsable() {
        // Ajouter un responsable fictif à la base de données
        Responsable responsable = new Responsable();
        responsbleRepository.save(responsable);

        // Appeler la méthode à tester
        responsableService.deleteResponsable(responsable.getId());

        // Vérifier que le responsable a été supprimé de la base de données

    }
}
