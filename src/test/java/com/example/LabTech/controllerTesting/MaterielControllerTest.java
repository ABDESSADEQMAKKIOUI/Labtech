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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
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
        materielDto.setId(1L);
        materielDto.setEchantillonId(1L);
        materielDto.setNom("materielNom1");
        materielDto.setEchantillonPatientId(1L);
        materielDto.setEchantillonDate_prend(new Date());
        materielDto.setEchantillonPatientNom("amani");

        materielDtos = new ArrayList<>();
        materielDtos.add(materielDto);

        materielDto2 = new MaterielDto(); // Initialize  materielDto
        materielDto2.setMaterielType(Materiel_type.AUTOMATE_ANALYSE);
        materielDto2.setId(2L);
        materielDto2.setEchantillonId(2L);
        materielDto2.setNom("materielNom2");
        materielDto2.setEchantillonPatientId(2L);
        materielDto2.setEchantillonDate_prend(new Date());
        materielDto2.setEchantillonPatientNom("kamali");
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
                .andExpect(jsonPath("$.echantillonPatientId", CoreMatchers.is(materielDto.getEchantillonPatientId().intValue())))
                .andExpect(jsonPath("$.materielType", CoreMatchers.is((materielDto.getMaterielType().toString()))))
                .andExpect(jsonPath("$.id", CoreMatchers.is((int) materielDto.getId())))
                .andExpect(jsonPath("$.echantillonId", CoreMatchers.is(( (int) materielDto.getEchantillonId()))))
                .andExpect(jsonPath("$.echantillonPatientNom", CoreMatchers.is((materielDto.getEchantillonPatientNom()))))
                .andReturn();


    }

    @Test
    public void getAllMaterielsTest() throws Exception {
        Mockito.when(materielService.getAllMateriel()).thenReturn(materielDtos);
        mockMvc.perform(get("/api/materiels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(materielDtos.size()))
                .andExpect(jsonPath("$[0].nom", CoreMatchers.is(materielDto.getNom())))
                .andExpect(jsonPath("$[0].echantillonPatientId", CoreMatchers.is(materielDto.getEchantillonPatientId().intValue())))
                .andExpect(jsonPath("$[0].materielType", CoreMatchers.is((materielDto.getMaterielType().toString()))))
                .andExpect(jsonPath("$[0].id", CoreMatchers.is((int) materielDto.getId())))
                .andExpect(jsonPath("$[0].echantillonId", CoreMatchers.is(( (int) materielDto.getEchantillonId()))))
                .andExpect(jsonPath("$[0].echantillonPatientNom", CoreMatchers.is((materielDto.getEchantillonPatientNom()))))
                .andExpect(jsonPath("$[1].nom", CoreMatchers.is(materielDto2.getNom())))
                .andExpect(jsonPath("$[1].echantillonPatientId", CoreMatchers.is(materielDto2.getEchantillonPatientId().intValue())))
                .andExpect(jsonPath("$[1].materielType", CoreMatchers.is((materielDto2.getMaterielType().toString()))))
                .andExpect(jsonPath("$[1].id", CoreMatchers.is((int) materielDto2.getId())))
                .andExpect(jsonPath("$[1].echantillonId", CoreMatchers.is(( (int) materielDto2.getEchantillonId()))))
                .andExpect(jsonPath("$[1].echantillonPatientNom", CoreMatchers.is((materielDto2.getEchantillonPatientNom()))))
                .andReturn();

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
                .andExpect(jsonPath("$.echantillonPatientId", CoreMatchers.is(materielDto.getEchantillonPatientId().intValue())))
                .andExpect(jsonPath("$.materielType", CoreMatchers.is((materielDto.getMaterielType().toString()))))
                .andExpect(jsonPath("$.id", CoreMatchers.is((int) materielDto.getId())))
                .andExpect(jsonPath("$.echantillonId", CoreMatchers.is(( (int) materielDto.getEchantillonId()))))
                .andExpect(jsonPath("$.echantillonPatientNom", CoreMatchers.is((materielDto.getEchantillonPatientNom()))))
                .andReturn();
    }

    @Test
    public void updateMaterielTest() throws Exception {
        Long materielId = 1L;
        when(materielService.getMaterielById(materielId)).thenReturn(Optional.of(materielDto));
        when(materielService.updateMateriel(materielDto)).thenReturn(materielDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/materiels/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materielDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(materielDto.getNom())))
                .andExpect(jsonPath("$.echantillonPatientId", CoreMatchers.is(materielDto.getEchantillonPatientId().intValue())))
                .andExpect(jsonPath("$.materielType", CoreMatchers.is((materielDto.getMaterielType().toString()))))
                .andExpect(jsonPath("$.id", CoreMatchers.is((int) materielDto.getId())))
                .andExpect(jsonPath("$.echantillonId", CoreMatchers.is(( (int) materielDto.getEchantillonId()))))
                .andExpect(jsonPath("$.echantillonPatientNom", CoreMatchers.is((materielDto.getEchantillonPatientNom()))))
                .andReturn();
    }
    @Test
    public void deleteMaterielTest() throws Exception {
        Long materielId = 1L;
        doNothing().when(materielService).deleteMateriel(materielId);

        ResultActions response = mockMvc.perform(delete("/api/materiels/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

}