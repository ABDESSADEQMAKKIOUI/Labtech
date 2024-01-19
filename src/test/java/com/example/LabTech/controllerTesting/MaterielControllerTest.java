package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.MaterielDto;
import com.example.LabTech.controller.MaterielController;
import com.example.LabTech.entite.Materiel;
import com.example.LabTech.entite.enums.Materiel_type;
import com.example.LabTech.entite.enums.Type_Analyse_name;
import com.example.LabTech.service.MaterielService;
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

@WebMvcTest(controllers = MaterielController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MaterielControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterielService materielService;

    @Autowired
    private ObjectMapper objectMapper;
    private MaterielDto materielDto, materielDto2;
    private List<MaterielDto> materielDtos;
    private Materiel materiel;

    @BeforeEach
    public void init() {
        materielDto = new MaterielDto(); // Initialize  materielDto
        materielDto.setMaterielType(Materiel_type.AUTOMATE_ANALYSE);
        materielDto.setTypeAnalyseId(1L);
        materielDto.setNom("materielNom1");
        materielDto.setTypeAnalyseTypeAnalyseName(Type_Analyse_name.Hormonologie);

        materielDtos = new ArrayList<>();
        materielDtos.add(materielDto);
        materielDto2 = new MaterielDto();
        materielDto2.setMaterielType(Materiel_type.ANALYSEUR_IMMUNOESSAI);
        materielDto2.setTypeAnalyseId(2L);
        materielDto.setNom("materielNom2");
        materielDto2.setTypeAnalyseTypeAnalyseName(Type_Analyse_name.Génétique);
        materielDtos.add(materielDto2);

    }
    @Test
    public void addMaterielTest() throws Exception {
        // Mocking the service behavior
        given(materielService.addMateriel(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/materiels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materielDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(materielDto.getNom())))
                .andExpect(jsonPath("$.materielType", CoreMatchers.is(materielDto.getMaterielType())))
                .andExpect(jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(materielDto.getTypeAnalyseTypeAnalyseName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(materielDto.getTypeAnalyseId())));
    }

    @Test
    public void getAllMaterielsTest() throws Exception {
        Mockito.when(materielService.getAllMateriel()).thenReturn(materielDtos);
        mockMvc.perform(get("/api/materiels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(materielDtos.size()))
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
    public void getMaterielByIdTest() throws Exception {
        Long materielId = 1L;
        Mockito.when(materielService.getMaterielById(materielId)).thenReturn(
                Optional.of(materielDto));

        ResultActions response = mockMvc.perform(get("/api/materiels/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materielDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(materielDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.materielType", CoreMatchers.is(materielDto.getMaterielType())))
                .andExpect(jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(materielDto.getTypeAnalyseTypeAnalyseName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(materielDto.getTypeAnalyseId())));
    }

    @Test
    public void updateMaterielTest() throws Exception {
        Long materielId = 1L;
        when(materielService.updateMateriel(materielDto)).thenReturn(materielDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/materiels/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materielDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(materielDto.getNom())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(materielDto.getTypeAnalyseId())))
                .andExpect(jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(materielDto.getTypeAnalyseTypeAnalyseName())))
                .andExpect(jsonPath("$.materielType", CoreMatchers.is(materielDto.getMaterielType())));
    }
    @Test
    public void deleteMaterielTest() throws Exception {
        Long materielId = 1L;
        doNothing().when(materielService).deleteMateriel(materielId);

        ResultActions response = mockMvc.perform(delete("/api/materiels/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}