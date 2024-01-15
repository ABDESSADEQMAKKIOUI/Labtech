package com.example.LabTech.service;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PlanificationService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnalyseRepository analyseRepository;
    
    
    public void affecterTechnicien(Technitien technitien, Test_analyse test_analyse) {
              test_analyse.setTechnitien(technitien);
              testRepository.save(test_analyse);
              
    }
    
    public void ChangerDateAnalyse(Analyse analyse, Date dateDebut, Date dateFin) {    	
    	analyse.setDate_debut(dateDebut);
    	analyse.setDate_fin(dateFin);
    	analyseRepository.save(analyse);     	
    }
    
    public void ChangerTechnicien(Technitien technitien, Test_analyse test_analyse) {
    	test_analyse.setTechnitien(technitien);
        testRepository.save(test_analyse);
    	
    }
    
   public void ChangerResponsable(Responsable responsable, Analyse analyse) {
	   analyse.setResponsable(responsable);
       analyseRepository.save(analyse);
   	
    	
    }
  
   public void AjouterTest(Analyse analyse, Test_analyse test_analyse) {	   
	   analyse.getTestAnalyses().add(test_analyse);
	   analyseRepository.save(analyse);   
	   
   }
   
   
    
    
}

