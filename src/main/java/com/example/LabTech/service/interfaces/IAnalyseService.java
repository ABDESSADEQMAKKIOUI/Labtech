package com.example.LabTech.service.interfaces;


import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.DTO.Type_AnalyseDto;

import java.util.List;
import java.util.Optional;

public interface  IAnalyseService{

    List<AnalyseDto> getAllAnalyses();

    Optional<AnalyseDto> getAnalyseById(long id);

    AnalyseDto addAnalyse(AnalyseDto analyse);

    AnalyseDto updateAnalyse(AnalyseDto analyse);

    void deleteAnalyse(long id);

    List<EnormDto> getEnormsByTypeAnalyseAndCreateTests(Type_AnalyseDto typeAnalyse, AnalyseDto analyse);
}
