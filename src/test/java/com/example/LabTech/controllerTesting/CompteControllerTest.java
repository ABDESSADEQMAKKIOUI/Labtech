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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private CompteDto compteDto, compteDto2;
    private List<CompteDto> compteDtos;
    private Compte compte;

    @BeforeEach
    public void init() {
        compteDto = new CompteDto(); // Initialize  compteDto
        compteDto.setId(1);
        compteDto.setUsername("sami");
        compteDto.setRole(Role.technicien);
        compteDto.setPassword("123");
        compteDtos = new ArrayList<>();
        compteDtos.add(compteDto);
        compteDto2 = new CompteDto();
        compteDto2.setId(2);
        compteDto2.setUsername("ahmed");
        compteDto2.setRole(Role.responsable);
        compteDto2.setPassword("123");
        compteDtos.add(compteDto2);

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
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is((int)compteDto.getId())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));
    }

    @Test
    public void getAllComptesTest() throws Exception {
        Mockito.when(compteService.getAllComptes()).thenReturn(compteDtos);
        compteDtos.forEach(System.out::println);
        mockMvc.perform(get("/api/comptes"))
                             .andExpect(status().isOk())
                             .andExpect(jsonPath("$.size()").value(compteDtos.size()))
                             .andExpect(jsonPath("$[0].id", CoreMatchers.is((int)compteDto.getId())))
                             .andExpect(jsonPath("$[0].username", CoreMatchers.is(compteDto.getUsername())))
                             .andExpect(jsonPath("$[0].role", CoreMatchers.is(compteDto.getRole().toString())))
                             .andExpect(jsonPath("$[0].password", CoreMatchers.is(compteDto.getPassword())))
                             .andExpect(jsonPath("$[1].id", CoreMatchers.is((int)compteDto2.getId())))
                             .andExpect(jsonPath("$[1].username", CoreMatchers.is(compteDto2.getUsername())))
                             .andExpect(jsonPath("$[1].role", CoreMatchers.is(compteDto2.getRole().toString())))
                             .andExpect(jsonPath("$[1].password", CoreMatchers.is(compteDto2.getPassword())))
                             .andReturn();

    }

    @Test
    public void getCompteByIdTest() throws Exception {
        Long compteId = 1L;
        Mockito.when(compteService.getCompteById(compteId)).thenReturn(
                Optional.of(compteDto));

        ResultActions response = mockMvc.perform(get("/api/comptes/{id}",compteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is((int)compteDto.getId())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));
    }

    @Test
    public void updateCompteTest() throws Exception {
        Long compteId = 1L;
        when(compteService.getCompteById(compteId)).thenReturn(Optional.of(compteDto));
        when(compteService.updateCompte(any(CompteDto.class))).thenReturn(compteDto);

        ResultActions response = mockMvc.perform(put("/api/comptes/{id}",compteId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto)));

        // Verifying HTTP status and JSON content
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is((int)compteDto.getId())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())))
                .andReturn();
    }
    @Test
    public void deleteCompteTest() throws Exception {
        Long compteId = 1L;
        doNothing().when(compteService).deleteCompte(compteId);

        ResultActions response = mockMvc.perform(delete("/api/comptes/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound());//.isOk());
    }

}