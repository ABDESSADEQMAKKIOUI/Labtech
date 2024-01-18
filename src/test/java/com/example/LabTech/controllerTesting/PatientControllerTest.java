package com.example.LabTech.controllerTesting;

import com.example.LabTech.controller.CompteController;
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

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.service.PatientService;

@WebMvcTest(controllers = CompteController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;
    private PatientDto patientDto, patientDto2;
    private List<PatientDto> patientDtos;
    private Patient patient;

    @BeforeEach
    public void init() {
        patientDto = new PatientDto(); // Initialize  patientDto
        patientDto.setNom("fawzi");
        patientDto.setPrenom("faysa");
        patientDto.setEmail("fawzi@gmail");


        patientDtos = new ArrayList<>();
        patientDtos.add(patientDto);

        patientDto2 = new PatientDto(); // Initialize  patientDto
        patientDto2.setNom("doumi");
        patientDto2.setPrenom("douna");
        patientDto2.setEmail("douna@gmail");

        patientDtos.add(patientDto2);

    }
    @Test
    public void addPatientTest() throws Exception {
        // Mocking the service behavior
        given(patientService.addPatient(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(patientDto.getNom())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(patientDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(patientDto.getEmail())));
    }

    @Test
    public void getAllPatientsTest() throws Exception {
        Mockito.when(patientService.getAllPatients()).thenReturn(patientDtos);
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(patientDtos.size()))
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
    public void getPatientByIdTest() throws Exception {
        Long patientId = 1L;
        PatientDto patientDto = new PatientDto(); // assuming you have initialized patientDto

        when(patientService.getPatientById(patientId)).thenReturn(Optional.of(patientDto));

        ResultActions response = mockMvc.perform(get("/api/patients/{id}", patientId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(patientDto.getNom())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(patientDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(patientDto.getEmail())));
    }

    @Test
    public void updatePatientTest() throws Exception {
        Long patientId = 1L;
        when(patientService.updatePatient(patientDto)).thenReturn(patientDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", CoreMatchers.is(patientDto.getNom())))
                .andExpect(jsonPath("$.prenom", CoreMatchers.is(patientDto.getPrenom())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(patientDto.getEmail())));
    }
    @Test
    public void deletePatientTest() throws Exception {
        Long patientId = 1L;
        doNothing().when(patientService).deletePatient(patientId);

        ResultActions response = mockMvc.perform(delete("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}