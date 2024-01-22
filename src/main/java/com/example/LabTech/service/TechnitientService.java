package com.example.LabTech.service;

import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.Exception.TecnitientNotFoundException;
import com.example.LabTech.entite.Technitien;
import com.example.LabTech.repository.TechnitienRepository;
import com.example.LabTech.service.interfaces.ITechnitientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnitientService implements ITechnitientService {

    @Autowired
    private TechnitienRepository technitienRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TechnitienDto> getAlltechnitiens() {
        List<Technitien> technitiens = technitienRepository.findAll();
        return technitiens.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TechnitienDto> gettechnitienById(long id) {
        Optional<Technitien> technitien = technitienRepository.findById(id);

        if (technitien.isPresent()) {
            return technitien.map(this::convertToDto);
        } else {
            throw new TecnitientNotFoundException("Technicien non trouvé avec l'ID : " + id);
        }
    }

   @Override
    public TechnitienDto addtechnitien(TechnitienDto technitienDTO) {
        Technitien technitien = convertToEntity(technitienDTO);
        return convertToDto(technitienRepository.save(technitien));
    }

    @Override
    public TechnitienDto updatetechnitien(TechnitienDto technitienDTO) {
        Technitien technitien = convertToEntity(technitienDTO);
        return convertToDto(technitienRepository.save(technitien));
    }

    @Override
    public void deletetechnitien(long id) {
        technitienRepository.deleteById(id);
    }

    // Helper methods for mapping
    private TechnitienDto convertToDto(Technitien technitien) {
        return modelMapper.map(technitien, TechnitienDto.class);
    }

    private Technitien convertToEntity(TechnitienDto technitienDTO) {
        return modelMapper.map(technitienDTO, Technitien.class);
    }
}
