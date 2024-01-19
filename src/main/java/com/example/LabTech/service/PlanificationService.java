package com.example.LabTech.service;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.interfaces.IPlanificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlanificationService implements IPlanificationService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void affecterTechnicien(TechnitienDto technitienDTO, Test_analyseDto testAnalyseDTO) {
        Technitien technitien = modelMapper.map(technitienDTO, Technitien.class);
        Test_analyse testAnalyse = modelMapper.map(testAnalyseDTO, Test_analyse.class);
        testAnalyse.setTechnitien(technitien);
        testRepository.save(testAnalyse);
    }

    @Override
    public void ChangerDateAnalyse(AnalyseDto analyseDTO, Date dateDebut, Date dateFin) {
        Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);
        analyse.setDate_debut(dateDebut);
        analyse.setDate_fin(dateFin);
        analyseRepository.save(analyse);
    }

    @Override
    public void ChangerTechnicien(TechnitienDto technitienDTO, Test_analyseDto testAnalyseDTO) {
        Technitien technitien = modelMapper.map(technitienDTO, Technitien.class);
        Test_analyse testAnalyse = modelMapper.map(testAnalyseDTO, Test_analyse.class);
        testAnalyse.setTechnitien(technitien);
        testRepository.save(testAnalyse);
    }

    @Override
    public void ChangerResponsable(ResponsableDto responsableDTO, AnalyseDto analyseDTO) {
        Responsable responsable = modelMapper.map(responsableDTO, Responsable.class);
        Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);
        analyse.setResponsable(responsable);
        analyseRepository.save(analyse);
    }

    @Override
    public void AjouterTest(AnalyseDto analyseDTO, Test_analyseDto testAnalyseDTO) {
        Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);
        Test_analyse testAnalyse = modelMapper.map(testAnalyseDTO, Test_analyse.class);
//        analyse.getTestAnalyses().add(testAnalyse);
        analyseRepository.save(analyse);
    }
}
