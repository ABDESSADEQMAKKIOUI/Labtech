package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.controller.TestController;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.enums.Role;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.service.CompteService;
import com.example.LabTech.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.entite.enums.Role;
import com.example.LabTech.service.CompteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Autowired
    private ObjectMapper objectMapper;
    private Test_analyseDto testDto, testDto2;
    private List<Test_analyseDto> testDtos;
    private Test_analyse test;

    @BeforeEach
    public void init() {
        testDto = new Test_analyseDto(); // Initialize  testDto
        testDto.setCommentaire("1commentaireTest-Analyse");
        testDto.setEnormName("EnormName1");
        testDto.setEnormId(1L);
        testDto.setResultat(10f);
        testDto.setStatus(Status.anormal);
        testDto.setEnormPlage_normale_max(20f);
        testDto.setTechnitienId(1L);
        testDto.setTechnitienNom("amine");
        testDto.setEnormPlage_normale_min(8f);
        testDto.setTypeAnalyseId(1L);
        testDto.setEnormUnite_mesure("10/5ml");
        testDto.setTypeAnalyseName("1typeAnalyseName1");

        testDtos = new ArrayList<>();
        testDtos.add(testDto);

        testDto2 = new Test_analyseDto(); // Initialize  testDto
        testDto2.setCommentaire("2commentaireTest-Analyse");
        testDto2.setEnormName("EnormName2");
        testDto2.setEnormId(2L);
        testDto2.setResultat(15f);
        testDto2.setStatus(Status.normal);
        testDto2.setEnormPlage_normale_max(25f);
        testDto2.setTechnitienId(1L);
        testDto2.setTechnitienNom("amine");
        testDto2.setEnormPlage_normale_min(10f);
        testDto2.setTypeAnalyseId(2L);
        testDto2.setEnormUnite_mesure("15/10ml");
        testDto2.setTypeAnalyseName("2typeAnalyseName2");
        testDtos.add(testDto2);

    }
    @Test
    public void addTestTest() throws Exception {
        // Mocking the service behavior
        given(testService.addTest(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/tests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.resultat", CoreMatchers.is(testDto.getResultat())))
                .andExpect(jsonPath("$.commentaire", CoreMatchers.is(testDto.getCommentaire())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(testDto.getStatus())))
                .andExpect(jsonPath("$.enormId", CoreMatchers.is(testDto.getEnormId())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.enormPlage_normale_max", CoreMatchers.is(testDto.getEnormPlage_normale_max())))
                .andExpect(jsonPath("$.enormPlage_normale_min", CoreMatchers.is(testDto.getEnormPlage_normale_min())))
                .andExpect(jsonPath("$.enormUnite_mesure", CoreMatchers.is(testDto.getEnormUnite_mesure())))
                .andExpect(jsonPath("$.technitienId", CoreMatchers.is(testDto.getTechnitienId())))
                .andExpect(jsonPath("$.technitienNom", CoreMatchers.is(testDto.getTechnitienNom())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(testDto.getTypeAnalyseId())));
    }

    @Test
    public void getAllTestsTest() throws Exception {
        Mockito.when(testService.getAllTests()).thenReturn(testDtos);
        mockMvc.perform(get("/api/tests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(testDtos.size()))
                .andDo(print());

//                             .andExpect(jsonPath("$[0].username").value("sami"))
//                             .andExpect(jsonPath("$[0].role").value("technicien"))
//
//                             .andExpect(jsonPath("$[1].id").value(2))
//                             .andExpect(jsonPath("$[1].username").value("ahmed"))
//                             .andExpect(jsonPath("$[1].role").value("responsable"))
//
//                             .andReturn();

    }

    @Test
    public void getTestByIdTest() throws Exception {
        Long testId = 1L;
        Mockito.when(testService.getTestById(testId)).thenReturn(
                Optional.of(testDto));

        ResultActions response = mockMvc.perform(get("/api/tests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.resultat", CoreMatchers.is(testDto.getResultat())))
                .andExpect(jsonPath("$.commentaire", CoreMatchers.is(testDto.getCommentaire())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(testDto.getStatus())))
                .andExpect(jsonPath("$.enormId", CoreMatchers.is(testDto.getEnormId())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.enormPlage_normale_max", CoreMatchers.is(testDto.getEnormPlage_normale_max())))
                .andExpect(jsonPath("$.enormPlage_normale_min", CoreMatchers.is(testDto.getEnormPlage_normale_min())))
                .andExpect(jsonPath("$.enormUnite_mesure", CoreMatchers.is(testDto.getEnormUnite_mesure())))
                .andExpect(jsonPath("$.technitienId", CoreMatchers.is(testDto.getTechnitienId())))
                .andExpect(jsonPath("$.technitienNom", CoreMatchers.is(testDto.getTechnitienNom())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(testDto.getTypeAnalyseId())));
    }

    @Test
    public void updateTestTest() throws Exception {
        Long testId = 1L;
        when(testService.updateTest(testDto)).thenReturn(testDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/tests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.resultat", CoreMatchers.is(testDto.getResultat())))
                .andExpect(jsonPath("$.commentaire", CoreMatchers.is(testDto.getCommentaire())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(testDto.getStatus())))
                .andExpect(jsonPath("$.enormId", CoreMatchers.is(testDto.getEnormId())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.enormPlage_normale_max", CoreMatchers.is(testDto.getEnormPlage_normale_max())))
                .andExpect(jsonPath("$.enormPlage_normale_min", CoreMatchers.is(testDto.getEnormPlage_normale_min())))
                .andExpect(jsonPath("$.enormUnite_mesure", CoreMatchers.is(testDto.getEnormUnite_mesure())))
                .andExpect(jsonPath("$.technitienId", CoreMatchers.is(testDto.getTechnitienId())))
                .andExpect(jsonPath("$.technitienNom", CoreMatchers.is(testDto.getTechnitienNom())))
                .andExpect(jsonPath("$.enormName", CoreMatchers.is(testDto.getEnormName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(testDto.getTypeAnalyseId())));
    }
    @Test
    public void deleteTestTest() throws Exception {
        Long testId = 1L;
        doNothing().when(testService).deleteTest(testId);

        ResultActions response = mockMvc.perform(delete("/api/tests/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}
