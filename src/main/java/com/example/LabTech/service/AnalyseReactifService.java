package com.example.LabTech.service;

import com.example.LabTech.DTO.AnalyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.AnalyseReactif;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.AnalyseReactifRepository;
import com.example.LabTech.service.interfaces.IAnalyseReactifService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyseReactifService implements IAnalyseReactifService {
    @Autowired
    private ModelMapper modelMapper; // Ajout du ModelMapper
    @Autowired
    AnalyseReactifRepository analyseReactifRepository ;

    @Transactional
    public void addAnalyse(Analyse analyse) {
        List<Type_Analyse> typeAnalyses = analyse.getTypeAnalyses();
        List<AnalyseReactif> analyseReactifs = analyse.getAnalyseReactifs();

        for (Type_Analyse typeAnalyse : typeAnalyses) {
            List<Test_analyse> testAnalyses = typeAnalyse.getTestAnalyses();
            for (Test_analyse testAnalyse : testAnalyses) {
                // Check if AnalyseReactif already exists
                Optional<AnalyseReactif> existingAnalyseReactif = analyseReactifs.stream()
                        .filter(ar -> ar.getAnalyse().equals(analyse) && ar.getReactif().equals(testAnalyse.getEnorm().getReactif()))
                        .findFirst();

                if (existingAnalyseReactif.isPresent()) {
                    // Increment quantity if AnalyseReactif exists
                    AnalyseReactif analyseReactif = existingAnalyseReactif.get();
                    analyseReactif.setQuantity(analyseReactif.getQuantity() + 1);
                    analyseReactifRepository.save(analyseReactif);
                } else {
                    // Add new AnalyseReactif if it doesn't exist
                    AnalyseReactif analyseReactif1 = new AnalyseReactif();
                    analyseReactif1.setReactif(testAnalyse.getEnorm().getReactif());
                    analyseReactif1.setAnalyse(analyse);
                    analyseReactif1.setQuantity(1);
                    analyseReactifs.add(analyseReactif1);
                    analyseReactifRepository.save(analyseReactif1);
                }
            }
        }
    }

    @Override
    public void addAnalyse(AnalyseDto analyseDto) {

    }
}
