package com.example.LabTech.service;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.AnalyseReactifRepository;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private TestRepository testAnalyseRepository;

    @Autowired
    private EnormRepository enormRepository;
    @Autowired
    AnalyseReactifService analyseReactifService ;

    @Autowired
    private ModelMapper modelMapper; // Ajout du ModelMapper

    public List<AnalyseDto> getAllAnalyses() {
        List<Analyse> analyses = analyseRepository.findAll();
        return analyses.stream()
                .map(analyse -> modelMapper.map(analyse, AnalyseDto.class))
                .collect(Collectors.toList());
    }

    public Optional<AnalyseDto> getAnalyseById(long id) {
        Optional<Analyse> analyse = analyseRepository.findById(id);
        return analyse.map(value -> modelMapper.map(value, AnalyseDto.class));
    }

    public AnalyseDto addAnalyse(AnalyseDto analyseDto) {
        Analyse analyse = modelMapper.map(analyseDto, Analyse.class);
        Analyse savedAnalyse = analyseRepository.save(analyse);
        return modelMapper.map(savedAnalyse, AnalyseDto.class);
    }

    public AnalyseDto updateAnalyse(AnalyseDto analyseDto, long id) {
        Optional<Analyse> existingAnalyse = analyseRepository.findById(id);
        if (existingAnalyse.isPresent()) {
            Analyse analyse = modelMapper.map(analyseDto, Analyse.class);
            analyse.setId(id);
            Analyse updatedAnalyse = analyseRepository.save(analyse);
            return modelMapper.map(updatedAnalyse, AnalyseDto.class);
        }
        return null; // Gérer le cas où l'analyse n'existe pas
    }

    public void deleteAnalyse(long id) {
        analyseRepository.deleteById(id);
    }

    public List<EnormDto> getEnormsByTypeAnalyseAndCreateTests(long typeAnalyseId, AnalyseDto analyseDto) {
        Type_Analyse typeAnalyse = new Type_Analyse();
        typeAnalyse.setId(typeAnalyseId);

        List<EnormDto> norms = enormRepository.findByTypeAnalyseId(typeAnalyse.getId()).stream()
                .map(enorm -> modelMapper.map(enorm, EnormDto.class))
                .collect(Collectors.toList());

        List<Test_analyse> tests = norms.stream()
                .map(enormDto -> {
                    Test_analyse testAnalyse = new Test_analyse();
                    testAnalyse.setEnorm(modelMapper.map(enormDto, Enorm.class));
//                    testAnalyse.setTypeAnalyse(modelMapper.map(analyseDto, Analyse.class));
                    return testAnalyse;
                })
                .collect(Collectors.toList());

        testAnalyseRepository.saveAll(tests);

        return norms;
    }
}
