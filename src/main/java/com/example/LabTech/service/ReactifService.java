package com.example.LabTech.service;

import com.example.LabTech.DTO.ReactifDto;
import com.example.LabTech.entite.Reactif;
import com.example.LabTech.repository.ReactifRepository;
import com.example.LabTech.service.interfaces.IReactifService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactifService implements IReactifService {

    @Autowired
    private ReactifRepository reactifRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReactifDto> getAllReactifs() {
        List<Reactif> reactifs = reactifRepository.findAll();
        return reactifs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReactifDto> getReactifById(long id) {
        Optional<Reactif> reactif = reactifRepository.findById(id);
        return reactif.map(this::convertToDto);
    }

    @Override
    public ReactifDto addReactif(ReactifDto reactifDTO) {
        Reactif reactif = convertToEntity(reactifDTO);
        return convertToDto(reactifRepository.save(reactif));
    }

    @Override
    public ReactifDto updateReactif(ReactifDto reactifDTO) {
        Reactif reactif = convertToEntity(reactifDTO);
        return convertToDto(reactifRepository.save(reactif));
    }

    @Override
    public void deleteReactif(long id) {
        reactifRepository.deleteById(id);
    }

    @Override
    public boolean checkQuantity(long reactifId) {
        Reactif reactif = reactifRepository.findById(reactifId).orElse(null);
        return reactif != null && reactif.getQuantity() >= 0 && reactif.getQuantity() < 5;
    }

    @Override
    public boolean checkExpirationDate(long reactifId) {
        Reactif reactif = reactifRepository.findById(reactifId).orElse(null);
        Date currentDate = new Date();
        return reactif != null && reactif.getDate_expiration() != null && currentDate.before(reactif.getDate_expiration());
    }

    // Helper methods for mapping
    private ReactifDto convertToDto(Reactif reactif) {
        return modelMapper.map(reactif, ReactifDto.class);
    }

    private Reactif convertToEntity(ReactifDto reactifDTO) {
        return modelMapper.map(reactifDTO, Reactif.class);
    }
}
