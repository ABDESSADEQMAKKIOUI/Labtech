package com.example.LabTech.controllerTesting;


import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.controller.CompteController;
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


@WebMvcTest(controllers = CompteController.class)
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
        technicienDto.setNom("mounir");
        technicienDto.setPrenom("mourad");
        technicienDto.setEmail("mounir@gmail");

        technitienDtos = new ArrayList<>();
        technitienDtos.add(technicienDto);
        technicienDto2 = new TechnitienDto(); // Initialize  technicienDto
        technicienDto2.setNom("kamal");
        technicienDto2.setPrenom("karim");
        technicienDto2.setEmail("kamal@gmail");
        technitienDtos.add(technicienDto2);

    }
    @Test
    public void addTechnitienTest() throws Exception {
        // Mocking the service behavior
        given(technicienService.addtechnitien(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/technitiens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }

    @Test
    public void getAllTechnitiensTest() throws Exception {
        Mockito.when(technicienService.getAlltechnitiens()).thenReturn(technitienDtos);
        mockMvc.perform(get("/api/technitiens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(technitienDtos.size()))
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
    public void getTechnitienByIdTest() throws Exception {
        Long technicienId = 1L;
        Mockito.when(technicienService.gettechnitienById(technicienId)).thenReturn(
                Optional.of(technicienDto));

        ResultActions response = mockMvc.perform(get("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }

    @Test
    public void updateTechnitienTest() throws Exception {
        Long technicienId = 1L;
        when(technicienService.updatetechnitien(technicienDto)).thenReturn(technicienDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(technicienDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(technicienDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", CoreMatchers.is(technicienDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(technicienDto.getEmail())));
    }
    @Test
    public void deleteTechnitienTest() throws Exception {
        Long technicienId = 1L;
        doNothing().when(technicienService).deletetechnitien(technicienId);

        ResultActions response = mockMvc.perform(delete("/api/technitiens/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}