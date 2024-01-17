package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;

import java.util.List;
import java.util.Optional;

public interface ITestService {

    List<Test_analyse> getAllTests();

    Optional<Test_analyse> getTestById(long id);

    Test_analyse addTest(Test_analyse testAnalyse);

    Test_analyse updateTest(Test_analyse testAnalyse);

    void deleteTest(long id);

    List<Test_analyse> getTestsByAnalyse(Analyse analyse);
}