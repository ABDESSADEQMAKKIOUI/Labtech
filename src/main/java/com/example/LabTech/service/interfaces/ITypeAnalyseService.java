package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Type_Analyse;

import java.util.List;
import java.util.Optional;

public interface ITypeAnalyseService {

    List<Type_Analyse> getAllttypeAnalyse();

    Optional<Type_Analyse> gettypeAnalyseById(long id);

    Type_Analyse addtypeAnalyse(Type_Analyse typeAnalyse);

    Type_Analyse updatetypeAnalyse(Type_Analyse typeAnalyse);

    void deletetypeAnalyse(long id);
}