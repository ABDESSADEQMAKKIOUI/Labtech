package com.example.LabTech.service;

import com.example.LabTech.entite.Responsable;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.ResponsbleRepository;
import com.example.LabTech.service.interfaces.IResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ResponsableService implements IResponsableService {
    @Autowired
    private ResponsbleRepository responsbleRepository;



    @Override
    public List<Responsable> getAllresponsable() {
        return responsbleRepository.findAll();
    }
    @Override
    public Optional<Responsable> getResponsableById(long id) {
        return responsbleRepository.findById(id);
    }
    @Override
    public Responsable addResponsable(Responsable responsable) {
        return responsbleRepository.save(responsable);
    }
    @Override
    public Responsable updateResponsable(Responsable responsable) {
        return responsbleRepository.save(responsable);
    }
    @Override
    public void deleteResponsable(long id) {
        responsbleRepository.deleteById(id);
    }


}


