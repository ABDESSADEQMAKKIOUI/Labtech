package com.example.LabTech.service.interfaces;


import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.DTO.TypeAnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.Type_Analyse;

import java.util.List;
import java.util.Optional;

public interface  IAnalyseService{

    List<AnalyseDto> getAllAnalyses();

    Optional<AnalyseDto> getAnalyseById(long id);

    AnalyseDto addAnalyse(AnalyseDto analyse);

    AnalyseDto updateAnalyse(AnalyseDto analyse);

    void deleteAnalyse(long id);

    List<EnormDto> getEnormsByTypeAnalyseAndCreateTests(TypeAnalyseDto typeAnalyse, AnalyseDto analyse);
}
