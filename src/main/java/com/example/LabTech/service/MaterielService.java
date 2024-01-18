package com.example.LabTech.service;

import com.example.LabTech.DTO.MaterielDto;
import com.example.LabTech.entite.Materiel;
import com.example.LabTech.repository.MaterielRepository;
import com.example.LabTech.service.interfaces.IMaterielService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterielService implements IMaterielService {

    @Autowired
    private MaterielRepository materielRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MaterielDto> getAllMateriel() {
        List<Materiel> materiels = materielRepository.findAll();
        return materiels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MaterielDto> getMaterielById(long id) {
        Optional<Materiel> materiel = materielRepository.findById(id);
        return materiel.map(this::convertToDto);
    }

    @Override
    public MaterielDto addMateriel(MaterielDto materielDTO) {
        Materiel materiel = convertToEntity(materielDTO);
        return convertToDto(materielRepository.save(materiel));
    }

    @Override
    public MaterielDto updateMateriel(MaterielDto materielDTO) {
        Materiel materiel = convertToEntity(materielDTO);
        return convertToDto(materielRepository.save(materiel));
    }

    @Override
    public void deleteMateriel(long id) {
        materielRepository.deleteById(id);
    }

    // Helper methods for mapping
    private MaterielDto convertToDto(Materiel materiel) {
        return modelMapper.map(materiel, MaterielDto.class);
    }

    private Materiel convertToEntity(MaterielDto materielDTO) {
        return modelMapper.map(materielDTO, Materiel.class);
    }
}
