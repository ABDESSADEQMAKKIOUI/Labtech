package com.example.LabTech.controllerTesting;


import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.controller.TechnitienController;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.service.TechnitientService;
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


@WebMvcTest(controllers = TechnitienController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TechnitienControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TechnitientService technicienService;

    @Autowired
    private ObjectMapper objectMapper;
    private TechnitienDto technicienDto, technicienDto2;
    private List<TechnitienDto> technitienDtos;
    private Technitien technitien;

    @BeforeEach
    public void init() {
        technicienDto = new TechnitienDto(); // Initialize  technicienDto
        technicienDto.setId(1L);
        technicienDto.setNom("mounir");
        technicienDto.setPrenom("mourad");
        technicienDto.setEmail("mounir@gmail");

        technitienDtos = new ArrayList<>();
        technitienDtos.add(technicienDto);
        technicienDto2 = new TechnitienDto(); // Initialize  technicienDto
        technicienDto2.setId(2L);
        technicienDto2.setNom("kamal");
        technicienDto2.setPrenom("karim");
        technicienDto2.setEmail("kamal@gmail");
        technitienDtos.add(technicienDto2);

    }
    @Test
    public void addTechnitienTest() throws Exception {
        // Mocking the service behavior
        when(technicienService.addtechnitien(ArgumentMatchers.any())).thenReturn(technicienDto);

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/technitiens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(jsonPath("$.id", CoreMatchers.is(technicienDto.getId().intValue())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }

    @Test
    public void getAllTechnitiensTest() throws Exception {
        Mockito.when(technicienService.getAlltechnitiens()).thenReturn(technitienDtos);
        mockMvc.perform(get("/api/technitiens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(technitienDtos.size()))
                .andExpect(jsonPath("$[0].nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(technicienDto.getId().intValue())))
                .andExpect(jsonPath("$[0].prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$[0].email", CoreMatchers.is(technicienDto.getEmail())))
                .andExpect(jsonPath("$[1].nom", CoreMatchers.is(technicienDto2.getNom())))
                .andExpect(jsonPath("$[1].id", CoreMatchers.is(technicienDto2.getId().intValue())))
                .andExpect(jsonPath("$[1].prenom", CoreMatchers.is(technicienDto2.getPrenom())))
                .andExpect(jsonPath("$[1].email", CoreMatchers.is(technicienDto2.getEmail())))
                .andDo(print());

    }

    @Test
    public void getTechnitienByIdTest() throws Exception {
        Long technicienId = 1L;
        Mockito.when(technicienService.gettechnitienById(technicienId)).thenReturn(
                Optional.of(technicienDto));

        ResultActions response = mockMvc.perform(get("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(jsonPath("$.id", CoreMatchers.is(technicienDto.getId().intValue())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }

    @Test
    public void updateTechnitienTest() throws Exception {
        Long technicienId = 1L;
        given(technicienService.gettechnitienById(technicienId)).willReturn(Optional.of(technicienDto));
        when(technicienService.updatetechnitien(technicienDto)).thenReturn(technicienDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(jsonPath("$.id", CoreMatchers.is(technicienDto.getId().intValue())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }
    @Test
    public void deleteTechnitienTest() throws Exception {
        Long technicienId = 1L;
        when(technicienService.gettechnitienById(technicienId)).thenReturn(Optional.of(technicienDto));
        doNothing().when(technicienService).deletetechnitien(technicienId);

        ResultActions response = mockMvc.perform(delete("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

}