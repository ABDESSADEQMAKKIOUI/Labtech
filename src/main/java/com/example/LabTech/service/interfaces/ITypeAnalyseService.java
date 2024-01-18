package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.TypeAnalyseDto;
import com.example.LabTech.entite.Type_Analyse;

import java.util.List;
import java.util.Optional;

public interface ITypeAnalyseService {

    List<TypeAnalyseDto> getAllttypeAnalyse();

    Optional<TypeAnalyseDto> gettypeAnalyseById(long id);

    TypeAnalyseDto addtypeAnalyse(TypeAnalyseDto typeAnalyse);

    TypeAnalyseDto updatetypeAnalyse(TypeAnalyseDto typeAnalyse);

    void deletetypeAnalyse(long id);
}