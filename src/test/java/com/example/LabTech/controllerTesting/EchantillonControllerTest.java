package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.controller.EchantillonController;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.entite.enums.Role;
import com.example.LabTech.service.CompteService;
import com.example.LabTech.service.EchantillonService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EchantillonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EchantillonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EchantillonService echantillonService;

    @Autowired
    private ObjectMapper objectMapper;
    private EchantillonDto echantillonDto, echantillonDto2;
    private List<EchantillonDto> echantillonDtos;
    private Echantillon echantillon;

    @BeforeEach
    public void init() {
        echantillonDto = new EchantillonDto(); // Initialize  echantillonDto
        echantillonDto.setPatientNom("fawaaz");
        echantillonDto.setPatientPrenom("fawzi");
        echantillonDto.setPatientAdress("Rue abc n 200");
        echantillonDto.setPatientTele("000000000");
        echantillonDto.setPatientEmail("fawaaz@gmail");
        echantillonDto.setDate_prend(new Date());
/*
        ArrayList<Dto> analyseDtoList = new ArrayList<>();

        // Ajout d'instances de AnalyseDto à la liste avec des valeurs
        analyseDtoList.add(new AnalyseDto(1, new Date(), new Date(), Status_Analyse.IN_PROGRESS, Status.ACTIVE, 123, Type_Analyse_name.TYPE1));
        analyseDtoList.add(new AnalyseDto(2, new Date(), new Date(), Status_Analyse.COMPLETED, Status.INACTIVE, 456, Type_Analyse_name.TYPE2));
*/
        //echantillonDto.setAnalyses(new ArrayList<>(){});

        echantillonDtos = new ArrayList<>();
        echantillonDtos.add(echantillonDto);
        echantillonDto2 = new EchantillonDto();
        echantillonDto2.setPatientNom("tamara");
        echantillonDto2.setPatientPrenom("ranam");
        echantillonDto2.setPatientAdress("Rue rgt n 100");
        echantillonDto2.setPatientTele("000000110");
        echantillonDto2.setPatientEmail("tamara@gmail");
        echantillonDto2.setDate_prend(new Date());
        echantillonDtos.add(echantillonDto2);

    }

    @Test
    public void addEchantillonTest() throws Exception {
        // Mocking the service behavior
 /*       given(compteService.addCompte(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/comptes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));*/
    }

    @Test
    public void getAllEchantillonsTest() throws Exception {/*
        Mockito.when(compteService.getAllComptes()).thenReturn(compteDtos);
        mockMvc.perform(get("/api/comptes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(compteDtos.size()))
                .andDo(print());

//                             .andExpect(jsonPath("$[0].username").value("sami"))
//                             .andExpect(jsonPath("$[0].role").value("technicien"))
//
//                             .andExpect(jsonPath("$[1].id").value(2))
//                             .andExpect(jsonPath("$[1].username").value("ahmed"))
//                             .andExpect(jsonPath("$[1].role").value("responsable"))
//
//                             .andReturn();
*/
    }

    @Test
    public void getEchantillonByIdTest() throws Exception {/*
        Long compteId = 1L;
        Mockito.when(compteService.getCompteById(compteId)).thenReturn(
                Optional.of(compteDto));

        ResultActions response = mockMvc.perform(get("/api/comptes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", CoreMatchers.is(compteDto.getPassword())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));*/
    }

    @Test
    public void deleteEchantillonTest() throws Exception {/*
        Long compteId = 1L;
        when(compteService.updateCompte(compteDto)).thenReturn(compteDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/comptes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compteDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", CoreMatchers.is(compteDto.getUsername())))
                .andExpect(jsonPath("$.role", CoreMatchers.is(compteDto.getRole().toString())));*/
    }

    @Test
    public void updateEchantillonTest() throws Exception {/*
        Long compteId = 1L;
        doNothing().when(compteService).deleteCompte(compteId);

        ResultActions response = mockMvc.perform(delete("/api/utilisateurs/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());*/
    }
}

