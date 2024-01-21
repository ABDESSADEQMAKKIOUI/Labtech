package com.example.LabTech.servicesTesting;

import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAnalyseTest {
    @Autowired
    TestService testService ;
    @Test
    void addtest(){
        Test_analyseDto testAnalyse1 = new Test_analyseDto();
        testAnalyse1.setEnormId(1L);
        testAnalyse1.setTechnitienId(1L);
        testAnalyse1.setTypeAnalyseId(1L);
        testAnalyse1.setResultat(10.5f);
        testAnalyse1.setCommentaire("Résultat normal");
        testAnalyse1.setStatus(Status.normal);

        Test_analyseDto testAnalyse2 = new Test_analyseDto();
        testAnalyse2.setEnormId(1L);
        testAnalyse2.setTechnitienId(2L);
        testAnalyse2.setTypeAnalyseId(1L);
        testAnalyse2.setResultat(25.3f);
        testAnalyse2.setCommentaire("Résultat anormal");
        testAnalyse2.setStatus(Status.anormal);

        Test_analyseDto testAnalyse5 = new Test_analyseDto();
        testAnalyse1.setEnormId(1L);
        testAnalyse1.setTechnitienId(1L);
        testAnalyse1.setTypeAnalyseId(2L);
        testAnalyse1.setResultat(10.5f);
        testAnalyse1.setCommentaire("Résultat normal");
        testAnalyse1.setStatus(Status.normal);

        Test_analyseDto testAnalyse4 = new Test_analyseDto();
        testAnalyse2.setEnormId(1L);
        testAnalyse2.setTechnitienId(2L);
        testAnalyse2.setTypeAnalyseId(2L);
        testAnalyse2.setResultat(25.3f);
        testAnalyse2.setCommentaire("Résultat anormal");
        testAnalyse2.setStatus(Status.anormal);

        Test_analyseDto testAnalyse3 = new Test_analyseDto();
        testAnalyse3.setEnormId(1L);
        testAnalyse3.setTechnitienId(2L);
        testAnalyse3.setTypeAnalyseId(1L);
        testAnalyse3.setResultat(15.8f);
        testAnalyse3.setCommentaire("Résultat normal");
        testAnalyse3.setStatus(Status.normal);
        testService.addTest(testAnalyse1);
        testService.addTest(testAnalyse5);
        testService.addTest(testAnalyse4);
        testService.addTest(testAnalyse2);
        testService.addTest(testAnalyse3);

    }
}
