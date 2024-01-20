package com.example.LabTech.service;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.AnalyseReactif;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.service.interfaces.IAnalyseReactifService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseReactifService implements IAnalyseReactifService {
    @Autowired
    private ModelMapper modelMapper; // Ajout du ModelMapper
    @Override
    public void Add(AnalyseDto analyseDto) {
        Analyse analyse = modelMapper.map(analyseDto, Analyse.class);
        List<Type_Analyse> typeAnalyses = analyse.getTypeAnalyses();
      for (Type_Analyse typeAnalyse : typeAnalyses){
          List<Test_analyse> testAnalyses = typeAnalyse.getTestAnalyses();
          for (Test_analyse testAnalyse : testAnalyses){
              AnalyseReactif analyseReactif = new AnalyseReactif() ;
              analyseReactif.setReactif(testAnalyse.getEnorm().getReactif());
              analyseReactif.setAnalyse(analyse);


          }
      }

    }
}
