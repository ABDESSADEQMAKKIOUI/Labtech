package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.controller.EchantillonController;
import com.example.LabTech.service.EchantillonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EchantillonController.class)
class EchantillonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EchantillonService echantillonService;
    EchantillonDto echantillonDto;


    @BeforeEach
    public void init() {
        echantillonDto = new EchantillonDto();
        echantillonDto.setId(1);
        echantillonDto.setPatientId(123L);
        echantillonDto.setPatientNom("ahmedi");
        echantillonDto.setPatientPrenom("Ahmed");
        echantillonDto.setDate_prend(new Date());
        echantillonDto.setMateriels(Collections.emptyList());
        echantillonDto.setAnalyses(Collections.emptyList());
    }
    @Test
    void getAllEchantillonsTest() throws Exception {
        // Mock the service method
        Mockito.when(echantillonService.getAllEchantillons())
                .thenReturn(Collections.singletonList(echantillonDto));

        // Performing HTTP GET request using MockMvc
        ResultActions response = mockMvc.perform(get("/api/echantillons"));

        // Verifying HTTP status and JSON content
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].patientId", is(123)))
                .andExpect(jsonPath("$[0].patientNom", is("ahmedi")))
                .andExpect(jsonPath("$[0].patientPrenom", is("Ahmed")))
                .andExpect(jsonPath("$[0].date_prend", is("2024-01-18T00:00:00.000Z")))
                .andExpect(jsonPath("$[0].materiels", hasSize(0)))
                .andExpect(jsonPath("$[0].analyses", hasSize(0)));

    }

    @Test
    void getEchantillonByIdTest() throws Exception {
        // Mock the service method that the controller calls
        Mockito.when(echantillonService.getEchantillonById(1))
                .thenReturn(java.util.Optional.of(echantillonDto));

        // Performing HTTP GET request using MockMvc
        ResultActions response = mockMvc.perform(get("/api/echantillons/1"));

        // Verifying HTTP status and JSON content
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.patientId", is(123)))
                .andExpect(jsonPath("$.patientNom", is("ahmedi")))
                .andExpect(jsonPath("$.patientPrenom", is("Ahmed")))
                .andExpect(jsonPath("$.date_prend", is("2024-01-18T00:00:00.000Z")))
                .andExpect(jsonPath("$.materiels", hasSize(0)))
                .andExpect(jsonPath("$.analyses", hasSize(0)));

    }

    @Test
    void addEchantillonTest() throws Exception {
        // Mock the service method that the controller calls
        Mockito.when(echantillonService.addEchantillon(ArgumentMatchers.any()))
                .thenReturn(echantillonDto);

        // Performing HTTP POST request using MockMvc
        ResultActions response = mockMvc.perform(post("/api/echantillons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(echantillonDto))); // Add the actual JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.patientId", is(123)))
                .andExpect(jsonPath("$.patientNom", is("ahmedi")))
                .andExpect(jsonPath("$.patientPrenom", is("Ahmed")))
                .andExpect(jsonPath("$.date_prend", is("2024-01-18T00:00:00.000Z")))
                .andExpect(jsonPath("$.materiels", hasSize(0)))
                .andExpect(jsonPath("$.analyses", hasSize(0)));

    }

    @Test
    void updateEchantillonTest() throws Exception {
        // Mock the service method that the controller calls
        Mockito.when(echantillonService.getEchantillonById(1))
                .thenReturn(java.util.Optional.of(echantillonDto));

        Mockito.when(echantillonService.updateEchantillon(ArgumentMatchers.any()))
                .thenReturn(echantillonDto);

        // Performing HTTP PUT request using MockMvc
        ResultActions response = mockMvc.perform(put("/api/echantillons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(echantillonDto))); // Add the actual JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.patientId", is(123)))
                .andExpect(jsonPath("$.patientNom", is("ahmedi")))
                .andExpect(jsonPath("$.patientPrenom", is("Ahmed")))
                .andExpect(jsonPath("$.date_prend", is("2024-01-18T00:00:00.000Z")))
                .andExpect(jsonPath("$.materiels", hasSize(0)))
                .andExpect(jsonPath("$.analyses", hasSize(0)));

    }

    @Test
    void deleteEchantillonTest() throws Exception {

        // Performing HTTP DELETE
        ResultActions response = mockMvc.perform(delete("/api/echantillons/1"));

        // Verifying HTTP status
        response.andExpect(status().isNoContent());

        //      Long echantillonId = 1L;
//        doNothing().when(echantillonService).deleteCompte(echantillonId);
//
//        ResultActions response = mockMvc.perform(delete("/api/echantillons/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(status().isOk());*/

    }


}

