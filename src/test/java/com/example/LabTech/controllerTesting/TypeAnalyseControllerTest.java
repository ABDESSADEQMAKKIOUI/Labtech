package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.*;
import com.example.LabTech.controller.TypeAnalyseController;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.service.TypeAnalyseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebMvcTest(controllers = TypeAnalyseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TypeAnalyseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeAnalyseService typeAnalyseService;

    @Autowired
    private ObjectMapper objectMapper;
    private Type_AnalyseDto typeAnalyseDto, typeAnalyseDto2;
    //private List<AnalyseDto.Type_AnalyseDto> typeAnalyseDtos;
    private Type_Analyse typeAnalyse;
    private Test_analyseDto testDto, testDto2;
    private List<Type_AnalyseDto> typeAnalyseDtos;

    @BeforeEach
    public void init() {

        // Create a Type_AnalyseDto instance with specific attribute values
        Type_AnalyseDto typeAnalyseDto = new Type_AnalyseDto();
        typeAnalyseDto.setId(1);
        typeAnalyseDto.setName("TestType");
        typeAnalyseDto.setAnalyseId(2);
        typeAnalyseDto.setAnalyseName("TestAnalyse");
        typeAnalyseDto.setAnalyseDate_debut(new Date());
        typeAnalyseDto.setAnalyseDate_fin(new Date());
        typeAnalyseDto.setAnalyseStatusAnalyse(Status_Analyse.en_cours);
        typeAnalyseDto.setAnalyseStatus(Status.anormal);

        // Create a Test_analyseDto instance with specific attribute values
        Type_AnalyseDto.Test_analyseDto testAnalyseDto = new Type_AnalyseDto.Test_analyseDto();
        testAnalyseDto.setId(3);
        testAnalyseDto.setResultat(98.5f);
        testAnalyseDto.setCommentaire("Good result");
        testAnalyseDto.setStatus(Status.normal);

        Type_AnalyseDto.Test_analyseDto testAnalyseDto2 = new Type_AnalyseDto.Test_analyseDto();
        testAnalyseDto2.setId(4);
        testAnalyseDto2.setResultat(88.5f);
        testAnalyseDto2.setCommentaire("Good result");
        testAnalyseDto2.setStatus(Status.normal);

        // Set the list of Test_analyseDto in the Type_AnalyseDto
        typeAnalyseDto.setTestAnalyses(Arrays.asList(testAnalyseDto, testAnalyseDto2));

        typeAnalyseDtos = new ArrayList<>();
        typeAnalyseDtos.add(typeAnalyseDto);

    }
    @Test
    public void addTypeAnalyseTest() throws Exception {
        // Mocking the service behavior
        given(typeAnalyseService.addtypeAnalyse(any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/type-analyses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(typeAnalyseDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.name", CoreMatchers.is("TestType")))
                .andExpect(jsonPath("$.analyseId", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.analyseName", CoreMatchers.is("TestAnalyse")))
                .andExpect(jsonPath("$.size()").value(typeAnalyseDtos.size()))
                .andExpect(jsonPath("$.testAnalyses", hasSize(1)))
                .andExpect(jsonPath("$.testAnalyses[0].id", CoreMatchers.is(3)))
                .andExpect(jsonPath("$.testAnalyses[0].resultat", CoreMatchers.is(98.5)))
                .andExpect(jsonPath("$.testAnalyses[0].commentaire", CoreMatchers.is("Good result")))
                .andExpect(jsonPath("$.testAnalyses[0].status", CoreMatchers.is("normal")));
    }

    @Test
    public void getAllTypeAnalysesTest() throws Exception {
        Mockito.when(typeAnalyseService.getAllttypeAnalyse()).thenReturn(typeAnalyseDtos);
        mockMvc.perform(get("/api/type-analyses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(typeAnalyseDtos.size()))
                .andDo(print());

    }

    @Test
    public void getTypeAnalyseByIdTest() throws Exception {
        Long typeAnalyseId = 1L;
        Mockito.when(typeAnalyseService.gettypeAnalyseById(typeAnalyseId)).thenReturn(
                Optional.of(typeAnalyseDto));

        ResultActions response = mockMvc.perform(get("/api/type-analyses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(typeAnalyseDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.name", CoreMatchers.is("TestType")))
                .andExpect(jsonPath("$.analyseId", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.analyseName", CoreMatchers.is("TestAnalyse")))
                .andExpect(jsonPath("$.testAnalyses", hasSize(1)))
                .andExpect(jsonPath("$.testAnalyses[0].id", CoreMatchers.is(3)))
                .andExpect(jsonPath("$.testAnalyses[0].resultat", CoreMatchers.is(98.5)))
                .andExpect(jsonPath("$.testAnalyses[0].commentaire", CoreMatchers.is("Good result")))
                .andExpect(jsonPath("$.testAnalyses[0].status", CoreMatchers.is("normal")));
    }

    @Test
    public void updateTypeAnalyseTest() throws Exception {
        Long typeAnalyseId = 1L;
        when(typeAnalyseService.updatetypeAnalyse(typeAnalyseDto)).thenReturn(typeAnalyseDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/type-analyses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(typeAnalyseDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.name", CoreMatchers.is("TestType")))
                .andExpect(jsonPath("$.analyseId", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.analyseName", CoreMatchers.is("TestAnalyse")))
                .andExpect(jsonPath("$.testAnalyses", hasSize(1)))
                .andExpect(jsonPath("$.testAnalyses[0].id", CoreMatchers.is(3)))
                .andExpect(jsonPath("$.testAnalyses[0].resultat", CoreMatchers.is(98.5)))
                .andExpect(jsonPath("$.testAnalyses[0].commentaire", CoreMatchers.is("Good result")))
                .andExpect(jsonPath("$.testAnalyses[0].status", CoreMatchers.is("normal")));
    }
    @Test
    public void deleteTypeAnalyseTest() throws Exception {
        Long typeAnalyseId = 1L;
        doNothing().when(typeAnalyseService).deletetypeAnalyse(typeAnalyseId);

        ResultActions response = mockMvc.perform(delete("/api/type-analyses/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}