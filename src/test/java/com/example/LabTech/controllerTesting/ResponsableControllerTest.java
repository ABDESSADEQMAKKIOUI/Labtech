package com.example.LabTech.controllerTesting;


import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.controller.ResponsableController;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.service.ResponsableService;
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


@WebMvcTest(controllers = ResponsableController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ResponsableControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponsableService responsableService;

    @Autowired
    private ObjectMapper objectMapper;
    private ResponsableDto responsableDto, responsableDto2;
    private List<ResponsableDto> responsableDtos;
    private Responsable responsable;

    @BeforeEach
    public void init() {
        responsableDto = new ResponsableDto(); // Initialize  responsableDto
        responsableDto.setNom("resAmine");
        responsableDto.setPrenom("resAhmed");
        responsableDto.setEmail("RESamine@gmail");

        responsableDtos = new ArrayList<>();
        responsableDtos.add(responsableDto);

        responsableDto2 = new ResponsableDto(); // Initialize  responsableDto
        responsableDto2.setNom("resAmine");
        responsableDto2.setPrenom("resAhmed");
        responsableDto2.setEmail("RESamine@gmail");
        responsableDtos.add(responsableDto2);

    }
    @Test
    public void addResponsableTest() throws Exception {
        // Mocking the service behavior
        given(responsableService.addResponsable(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/responsables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responsableDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(responsableDto.getNom())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(responsableDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(responsableDto.getEmail())));
    }

    @Test
    public void getAllResponsablesTest() throws Exception {
        Mockito.when(responsableService.getAllresponsable()).thenReturn(responsableDtos);
        mockMvc.perform(get("/api/responsables"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(responsableDtos.size()))
                .andDo(print());

    }

    @Test
    public void getResponsableByIdTest() throws Exception {
        Long responsableId = 1L;
        Mockito.when(responsableService.getResponsableById(responsableId)).thenReturn(
                Optional.of(responsableDto));

        ResultActions response = mockMvc.perform(get("/api/responsables/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responsableDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(responsableDto.getNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom", CoreMatchers.is(responsableDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(responsableDto.getEmail())));
    }

    @Test
    public void updateResponsableTest() throws Exception {
        Long responsableId = 1L;
        responsableDto.setId(1L);
        when(responsableService.getResponsableById(responsableId)).thenReturn(Optional.of(responsableDto));
        when(responsableService.updateResponsable(responsableDto)).thenReturn(responsableDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/responsables/{id}",responsableId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responsableDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(responsableDto.getNom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(responsableDto.getEmail())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(responsableDto.getPrenom())))
                .andDo(print());
    }
    @Test
    public void deleteResponsableTest() throws Exception {
        Long responsableId = 1L;
        when(responsableService.getResponsableById(responsableId)).thenReturn(Optional.of(responsableDto));
        doNothing().when(responsableService).deleteResponsable(responsableId);

        ResultActions response = mockMvc.perform(delete("/api/responsables/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }

}