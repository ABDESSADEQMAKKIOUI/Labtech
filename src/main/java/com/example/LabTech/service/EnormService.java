package com.example.LabTech.service;

import com.example.LabTech.DTO.EnormDto;
import com.example.LabTech.entite.Enorm;
import com.example.LabTech.repository.EnormRepository;
import com.example.LabTech.service.interfaces.IEnormService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnormService implements IEnormService {

    @Autowired
    private EnormRepository enormRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EnormDto> getAllEnorms() {
        List<Enorm> norms = enormRepository.findAll();
        return norms.stream()
                .map(enorm -> modelMapper.map(enorm, EnormDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EnormDto> getEnormsById(long id) {
        return enormRepository.findById(id)
                .map(enorm -> modelMapper.map(enorm, EnormDto.class));
    }

    @Override
    public EnormDto addEnorms(EnormDto enormDto) {
        Enorm enorm = modelMapper.map(enormDto, Enorm.class);
        Enorm savedEnorm = enormRepository.save(enorm);
        return modelMapper.map(savedEnorm, EnormDto.class);
    }

    @Override
    public EnormDto updateEnorms(EnormDto enormDto) {
        Enorm existingEnorm = enormRepository.findById(enormDto.getId()).orElse(null);
        if (existingEnorm != null) {
            modelMapper.map(enormDto, existingEnorm);
            Enorm updatedEnorm = enormRepository.save(existingEnorm);
            return modelMapper.map(updatedEnorm, EnormDto.class);
        }
        return null; // Handle not found or any other appropriate action
    }

    @Override
    public void deleteEnorms(long id) {
        enormRepository.deleteById(id);
    }
}
