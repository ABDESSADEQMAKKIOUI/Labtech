package com.example.LabTech.service.interfaces;


import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.Type_Analyse;

import java.util.List;
import java.util.Optional;

public interface  IAnalyseService{

    List<Analyse> getAllAnalyses();

    Optional<Analyse> getAnalyseById(long id);

    Analyse addAnalyse(Analyse analyse);

    Analyse updateAnalyse(Analyse analyse);

    void deleteAnalyse(long id);

    List<Enorm> getEnormsByTypeAnalyseAndCreateTests(Type_Analyse typeAnalyse, Analyse analyse);
}
