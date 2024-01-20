package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.DTO.FournisseurDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.controller.FournisseurController;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.entite.enums.Role;
import com.example.LabTech.service.CompteService;
import com.example.LabTech.service.FournisseurService;
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
import static org.mockito.BDDMockito.*;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FournisseurController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FournisseurControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FournisseurService fournisseurService;

    @Autowired
    private ObjectMapper objectMapper;
    private FournisseurDto fournisseurDto, fournisseurDto2;
    private List<FournisseurDto> fournisseurDtos;
    private Fournisseur fournisseur;

    @BeforeEach
    public void init() {
        fournisseurDto = new FournisseurDto(); // Initialize  fournisseurDto
        fournisseurDto.setNom("FournisseurName");
        fournisseurDtos = new ArrayList<>();
        fournisseurDtos.add(fournisseurDto);
        fournisseurDto2 = new FournisseurDto();
        fournisseurDto2.setNom("FournissseurName2");
        fournisseurDtos.add(fournisseurDto2);

    }
    @Test
    public void addFournisseurTest() throws Exception {
        // Mocking the service behavior
        given(fournisseurService.addFournisseur(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/comptes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fournisseurDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(fournisseurDto.getNom())));
    }

    @Test
    public void getAllFournisseursTest() throws Exception {
        Mockito.when(fournisseurService.getAllFournisseurs()).thenReturn(fournisseurDtos);
        mockMvc.perform(get("/api/fournisseurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(fournisseurDtos.size()))
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
    public void getFournisseurByIdTest() throws Exception {
        Long fournisseurId = 1L;
        Mockito.when(fournisseurService.getFournisseurById(fournisseurId)).thenReturn(
                Optional.of(fournisseurDto));

        ResultActions response = mockMvc.perform(get("/api/fournisseurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fournisseurDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(fournisseurDto.getNom())));
    }

    @Test
    public void updateFournisseurTest() throws Exception {
        Long fournisseurId = 1L;
        when(fournisseurService.updateFournisseur(fournisseurDto)).thenReturn(fournisseurDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/fournisseurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fournisseurDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", CoreMatchers.is(fournisseurDto.getNom())));
    }
    @Test
    public void deleteFournisseurTest() throws Exception {
        Long fournisseurId = 1L;
        doNothing().when(fournisseurService).deleteFournisseur(fournisseurId);

        ResultActions response = mockMvc.perform(delete("/api/fournisseurs/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}