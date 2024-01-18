package com.example.LabTech.controllerTesting;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.controller.CompteController;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.enums.Type_Analyse_name;
import com.example.LabTech.service.EnormService;
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
public class EnormControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnormService enormService;

    @Autowired
    private ObjectMapper objectMapper;
    private EnormDto enormDto, enormDto2;
    private List<EnormDto> enormDtos;
    private Enorm enorm;

    @BeforeEach
    public void init() {
        enormDto = new EnormDto(); // Initialize  compteDto
        enormDto.setName("enormNom");
        enormDto.setReactifNom("reactifNom");
        enormDto.setPlage_normale_min(10f);
        enormDto.setUnite_mesure("Unitedemesure");
        enormDto.setPlage_normale_max(50f);

        enormDto.setTypeAnalyseTypeAnalyseName(Type_Analyse_name.Biochimie);
        enormDto.setTypeAnalyseId(1L);

        enormDtos = new ArrayList<>();
        enormDtos.add(enormDto);
        enormDto2 = new EnormDto(); // Initialize  compteDto
        enormDto2.setName("enormNom2");
        enormDto2.setReactifNom("reactifNom2");
        enormDto2.setPlage_normale_min(20f);
        enormDto2.setUnite_mesure("Unitedemesure2");
        enormDto2.setPlage_normale_max(70f);

        enormDto2.setTypeAnalyseTypeAnalyseName(Type_Analyse_name.Coagulation);
        enormDto2.setTypeAnalyseId(2L);
        enormDtos.add(enormDto2);

    }
    @Test
    public void addEnormTest() throws Exception {
        // Mocking the service behavior
        given(enormService.addEnorms(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/enorms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enormDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(enormDto.getTypeAnalyseTypeAnalyseName())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(enormDto.getTypeAnalyseId())))
                .andExpect(jsonPath("$.id", CoreMatchers.is(enormDto.getId())))
                .andExpect(jsonPath("$.reactifNom", CoreMatchers.is(enormDto.getReactifNom())))
                .andExpect(jsonPath("$.plage_normale_min", CoreMatchers.is(enormDto.getPlage_normale_min())))
                .andExpect(jsonPath("$.plage_normale_max", CoreMatchers.is(enormDto.getPlage_normale_max())))
                .andExpect(jsonPath("$.unite_mesure", CoreMatchers.is(enormDto.getUnite_mesure())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(enormDto.getName())));
    }

    @Test
    public void getAllEnormsTest() throws Exception {
        Mockito.when(enormService.getAllEnorms()).thenReturn(enormDtos);
        mockMvc.perform(get("/api/enorms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(enormDtos.size()))
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
    public void getEnormByIdTest() throws Exception {
        Long enormId = 1L;
        Mockito.when(enormService.getEnormsById(enormId)).thenReturn(
                Optional.of(enormDto));

        ResultActions response = mockMvc.perform(get("/api/enorms/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enormDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(enormDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(enormDto.getTypeAnalyseTypeAnalyseName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(enormDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeAnalyseId", CoreMatchers.is(enormDto.getTypeAnalyseId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plage_normale_max", CoreMatchers.is(enormDto.getPlage_normale_max())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plage_normale_min", CoreMatchers.is(enormDto.getPlage_normale_min())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reactifNom", CoreMatchers.is(enormDto.getReactifNom())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.unite_mesure", CoreMatchers.is(enormDto.getUnite_mesure())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(enormDto.getId())));

                    }

    @Test
    public void updateEnormTest() throws Exception {
        Long enormId = 1L;
        when(enormService.updateEnorms(enormDto)).thenReturn(enormDto);

        ResultActions response;
        response = mockMvc.perform(put("/api/enorms/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enormDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(enormDto.getId())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(enormDto.getName())))
                .andExpect(jsonPath("$.plage_normale_min", CoreMatchers.is(enormDto.getPlage_normale_min())))
                .andExpect(jsonPath("$.reactifNom", CoreMatchers.is(enormDto.getReactifNom())))
                .andExpect(jsonPath("$.plage_normale_max", CoreMatchers.is(enormDto.getPlage_normale_max())))
                .andExpect(jsonPath("$.unite_mesure", CoreMatchers.is(enormDto.getUnite_mesure())))
                .andExpect(jsonPath("$.typeAnalyseId", CoreMatchers.is(enormDto.getTypeAnalyseId())))
                .andExpect(jsonPath("$.typeAnalyseTypeAnalyseName", CoreMatchers.is(enormDto.getTypeAnalyseTypeAnalyseName())));

    }
    @Test
    public void deleteEnormTest() throws Exception {
        Long enormId = 1L;
        doNothing().when(enormService).deleteEnorms(enormId);

        ResultActions response = mockMvc.perform(delete("/api/enorms/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}
