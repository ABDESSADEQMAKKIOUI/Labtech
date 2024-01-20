package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.Type_AnalyseDto;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.TypeAnalyseRepository;
import com.example.LabTech.service.TypeAnalyseService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TypeAnalyseServiceTest {

    @Autowired
    private TypeAnalyseService typeAnalyseService;

    @Autowired
    private TypeAnalyseRepository typeAnalyseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testGetAllttypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyse1 = createSampleTypeAnalyse();
        Type_Analyse typeAnalyse2 = createSampleTypeAnalyse();
        typeAnalyseRepository.save(typeAnalyse1);
        typeAnalyseRepository.save(typeAnalyse2);

        // Act
        List<Type_AnalyseDto> allTypeAnalyses = typeAnalyseService.getAllttypeAnalyse();

        // Assert
        assertNotNull(allTypeAnalyses, "List of TypeAnalyses should not be null");
        assertEquals(2, allTypeAnalyses.size(), "List should contain 2 TypeAnalyses");
    }

    @Test
    public void testGettypeAnalyseById() {
        // Arrange
        Type_Analyse typeAnalyse = createSampleTypeAnalyse();
        typeAnalyseRepository.save(typeAnalyse);

        // Act
        Optional<Type_AnalyseDto> foundTypeAnalyse = typeAnalyseService.gettypeAnalyseById(typeAnalyse.getId());

        // Assert
        assertTrue(foundTypeAnalyse.isPresent(), "TypeAnalyse should be present");
        assertEquals(typeAnalyse.getId(), foundTypeAnalyse.get().getId(), "Found TypeAnalyse ID should match the expected ID");
    }

    @Test
    public void testAddtypeAnalyse() {
        // Arrange
//        Type_AnalyseDto typeAnalyseToAdd = createSampleTypeAnalyseDto();
//
//        // Act
//        Type_AnalyseDto addedTypeAnalyse = typeAnalyseService.addtypeAnalyse(typeAnalyseToAdd);
//
//        // Assert
//        assertNotNull(addedTypeAnalyse, "Added TypeAnalyse should not be null");
//        assertNotNull(addedTypeAnalyse.getId(), "Added TypeAnalyse ID should not be null");
        Type_AnalyseDto typeAnalyse1 = new Type_AnalyseDto();

        typeAnalyse1.setName("Hématologie");
        typeAnalyse1.setAnalyseId(1L);

        Type_AnalyseDto typeAnalyse2 = new Type_AnalyseDto();

        typeAnalyse2.setName("Biochimie");
        typeAnalyse2.setAnalyseId(1L);

        Type_AnalyseDto typeAnalyse3 = new Type_AnalyseDto();

        typeAnalyse3.setName("Immunologie");
        typeAnalyse3.setAnalyseId(1L);

        Type_AnalyseDto typeAnalyse4 = new Type_AnalyseDto();

        typeAnalyse4.setName("Microbiologie");
        typeAnalyse4.setAnalyseId(2L);

        Type_AnalyseDto typeAnalyse5 = new Type_AnalyseDto();

        typeAnalyse5.setName("Hormonologie");
        typeAnalyse5.setAnalyseId(2L);

        Type_AnalyseDto typeAnalyse6 = new Type_AnalyseDto();

        typeAnalyse6.setName("Coagulation");
        typeAnalyse6.setAnalyseId(2L);
        typeAnalyseService.addtypeAnalyse(typeAnalyse6);
        typeAnalyseService.addtypeAnalyse(typeAnalyse1);
        typeAnalyseService.addtypeAnalyse(typeAnalyse4);
        typeAnalyseService.addtypeAnalyse(typeAnalyse3);
        typeAnalyseService.addtypeAnalyse(typeAnalyse5);
        typeAnalyseService.addtypeAnalyse(typeAnalyse2);

    }

    @Test
    public void testUpdatetypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyse = createSampleTypeAnalyse();
        typeAnalyseRepository.save(typeAnalyse);

        Type_AnalyseDto updatedTypeAnalyseDto = createSampleTypeAnalyseDto();
        updatedTypeAnalyseDto.setId(typeAnalyse.getId());

        // Act
        Type_AnalyseDto updatedTypeAnalyse = typeAnalyseService.updatetypeAnalyse(updatedTypeAnalyseDto);

        // Assert
        assertNotNull(updatedTypeAnalyse, "Updated TypeAnalyse should not be null");
        assertEquals(typeAnalyse.getId(), updatedTypeAnalyse.getId(), "Updated TypeAnalyse ID should match the original ID");
    }

    @Test
    public void testDeletetypeAnalyse() {
        // Arrange
        Type_Analyse typeAnalyse = createSampleTypeAnalyse();
        typeAnalyseRepository.save(typeAnalyse);

        // Act
        typeAnalyseService.deletetypeAnalyse(typeAnalyse.getId());

        // Assert
        Optional<Type_AnalyseDto> deletedTypeAnalyse = typeAnalyseService.gettypeAnalyseById(typeAnalyse.getId());
        assertFalse(deletedTypeAnalyse.isPresent(), "TypeAnalyse should be deleted");
    }

    private Type_Analyse createSampleTypeAnalyse() {
        Type_Analyse typeAnalyse = new Type_Analyse();

        return typeAnalyse;
    }

    private Type_AnalyseDto createSampleTypeAnalyseDto() {
        Type_AnalyseDto typeAnalyseDto = new Type_AnalyseDto();

        return typeAnalyseDto;
    }
}
