package com.example.LabTech.service;

import com.example.LabTech.DTO.ResponsableDto;
import com.example.LabTech.entite.Responsable;
import com.example.LabTech.repository.ResponsbleRepository;
import com.example.LabTech.service.interfaces.IResponsableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsableService implements IResponsableService {

    @Autowired
    private ResponsbleRepository responsbleRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<ResponsableDto> getAllresponsable() {
        List<Responsable> responsables = responsbleRepository.findAll();
        return responsables.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ResponsableDto> getResponsableById(long id) {
        Optional<Responsable> responsable = responsbleRepository.findById(id);
        return responsable.map(this::convertToDto);
    }

    @Override
    public ResponsableDto addResponsable(ResponsableDto responsableDTO) {
        Responsable responsable = convertToEntity(responsableDTO);
        return convertToDto(responsbleRepository.save(responsable));
    }

    @Override
    public ResponsableDto updateResponsable(ResponsableDto responsableDTO) {
        Responsable responsable = convertToEntity(responsableDTO);
        return convertToDto(responsbleRepository.save(responsable));
    }

    @Override
    public void deleteResponsable(long id) {
        responsbleRepository.deleteById(id);
    }

    // Helper methods for mapping
    private ResponsableDto convertToDto(Responsable responsable) {
        return modelMapper.map(responsable, ResponsableDto.class);
    }

    private Responsable convertToEntity(ResponsableDto responsableDTO) {
        return modelMapper.map(responsableDTO, Responsable.class);
    }
}
