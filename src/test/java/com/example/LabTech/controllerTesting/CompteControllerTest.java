package com.example.LabTech.controllerTesting;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = CompteController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CompteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompteService compteService;

    @Autowired
    private ObjectMapper objectMapper;
    private CompteDto compteDto;
    private Compte compte;

    @BeforeEach
    public void init() {
        compteDto = new CompteDto(); // Initialize  compteDto
        compteDto.setUsername("sami");
        compteDto.setRole(Role.technicien);
    }
    @Test
    public void addCompteTest() throws Exception {
        // Mocking the service behavior
        given(compteService.addCompte(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/comptes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));
    }



}