package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.entite.Analyse;

import java.util.List;
import java.util.Optional;

public interface ITestService {

    List<Test_analyseDto> getAllTests();

    Optional<Test_analyseDto> getTestById(long id);

    Test_analyseDto addTest(Test_analyseDto testAnalyse);

    Test_analyseDto updateTest(Test_analyseDto testAnalyse);

    void deleteTest(long id);

    List<Test_analyseDto> getTestsByAnalyse(Analyse analyse);
}