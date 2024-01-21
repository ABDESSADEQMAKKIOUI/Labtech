package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.service.AnalyseReactifService;
import com.example.LabTech.service.AnalyseService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class AnalyseReactifServiceTest {

  @Autowired
   private AnalyseReactifService analyseReactifService ;
  @Autowired
  private AnalyseService analyseService ;
    @Autowired
    private ModelMapper modelMapper ;
    @Test
    @Rollback
    @Transactional
    void testAddAnalyse() {
        Optional<AnalyseDto> analyse = analyseService.getAnalyseById(1L);
        Analyse  analyse1 = modelMapper.map(analyse, Analyse.class);
        analyseReactifService.addAnalyse(analyse1);

    }
    }
