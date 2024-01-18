package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.TestAnalyseDto;
import com.example.LabTech.DTO.TypeAnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;

import java.util.List;
import java.util.Optional;

public interface ITestService {

    List<TestAnalyseDto> getAllTests();

    Optional<TestAnalyseDto> getTestById(long id);

    TestAnalyseDto addTest(TestAnalyseDto testAnalyse);

    TestAnalyseDto updateTest(TestAnalyseDto testAnalyse);

    void deleteTest(long id);

    List<TypeAnalyseDto> getTestsByAnalyse(Analyse analyse);
}