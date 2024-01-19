package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.Type_AnalyseDto;

import java.util.List;
import java.util.Optional;

public interface ITypeAnalyseService {

    List<Type_AnalyseDto> getAllttypeAnalyse();

    Optional<Type_AnalyseDto> gettypeAnalyseById(long id);

    Type_AnalyseDto addtypeAnalyse(Type_AnalyseDto typeAnalyse);

    Type_AnalyseDto updatetypeAnalyse(Type_AnalyseDto typeAnalyse);

    void deletetypeAnalyse(long id);
}