package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.ReactifDto;
import com.example.LabTech.controller.MaterielController;
import com.example.LabTech.entite.Reactif;
import com.example.LabTech.service.ReactifService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
public class ReactifControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReactifService reactifService;

    @Autowired
    private ObjectMapper objectMapper;
    private ReactifDto reactifDto, reactifDto2;
    private List<ReactifDto> reactifDtos;
    private Reactif reactif;

    @BeforeEach
    public void init() {
        reactifDto = new ReactifDto(); // Initialize  reactifDto
        reactifDto.setNom("reactifName1");
        reactifDto.setQuantity(100);
        reactifDto.setDate_expiration(new Date());

        reactifDtos = new ArrayList<>();
        reactifDtos.add(reactifDto);

        reactifDto2 = new ReactifDto(); // Initialize  reactifDto
        reactifDto2.setNom("reactifName2");
        reactifDto2.setQuantity(200);
        reactifDto2.setDate_expiration(new Date());

        reactifDtos.add(reactifDto2);

    }
    /*
    @Test
    public void addReactifTest() throws Exception {
        // Mocking the service behavior
        given(reactifService.addReactif(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/reactifs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reactifDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(reactifDto.getNom())))
                .andExpect(jsonPath("$.quantity", CoreMatchers.is(reactifDto.getQuantity())))
                .andExpect(jsonPath("$.date_expiration", CoreMatchers.is(reactifDto.getDate_expiration())));
    }
*/
    @Test
    public void getAllReactifsTest() throws Exception {
        Mockito.when(reactifService.getAllReactifs()).thenReturn(reactifDtos);
        mockMvc.perform(get("/api/reactifs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(reactifDtos.size()))
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
    public void getReactifByIdTest() throws Exception {
        Long reactifId = 1L;
        Mockito.when(reactifService.getReactifById(reactifId)).thenReturn(
                Optional.of(reactifDto));

        ResultActions response = mockMvc.perform(get("/api/reactifs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reactifDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(reactifDto.getNom())))
                .andExpect(jsonPath("$.quantity", CoreMatchers.is(reactifDto.getQuantity())))
                .andExpect(jsonPath("$.date_expiration", CoreMatchers.is(reactifDto.getDate_expiration())));
    }

    @Test
    public void updateReactifTest() throws Exception {
        Long reactifId = 1L;
        when(reactifService.updateReactif(reactifDto)).thenReturn(reactifDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/reactifs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reactifDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(reactifDto.getNom())))
                .andExpect(jsonPath("$.date_expiration", CoreMatchers.is(reactifDto.getDate_expiration())))
                .andExpect(jsonPath("$.quantity", CoreMatchers.is(reactifDto.getQuantity())));
    }
    @Test
    public void deleteReactifTest() throws Exception {
        Long reactifId = 1L;
        doNothing().when(reactifService).deleteReactif(reactifId);

        ResultActions response = mockMvc.perform(delete("/api/reactifs/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}