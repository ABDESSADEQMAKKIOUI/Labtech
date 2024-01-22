package com.example.LabTech.service;

import com.example.LabTech.DTO.EchantillonDto;
import com.example.LabTech.Exception.EchantillonNotFoundException;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.repository.EchantillonRepository;
import com.example.LabTech.service.interfaces.IEchantillonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EchantillonService implements IEchantillonService {

    @Autowired
    private EchantillonRepository echantillonRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Méthodes de service pour la logique métier liée à Echantillon
    @Override
    public List<EchantillonDto> getAllEchantillons() {
        List<Echantillon> echantillons = echantillonRepository.findAll();
        return echantillons.stream()
                .map(echantillon -> modelMapper.map(echantillon, EchantillonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EchantillonDto> getEchantillonById(long id) {
        Optional<Echantillon> echantillon = echantillonRepository.findById(id);

        if (echantillon.isPresent()) {
            return echantillon.map(value -> modelMapper.map(value, EchantillonDto.class));
        } else {
            throw new EchantillonNotFoundException("Échantillon non trouvé avec l'ID : " + id);
        }

    }

    @Override
    public EchantillonDto addEchantillon(EchantillonDto echantillonDto) {
        Echantillon echantillon = modelMapper.map(echantillonDto, Echantillon.class);
        Echantillon savedEchantillon = echantillonRepository.save(echantillon);
        return modelMapper.map(savedEchantillon, EchantillonDto.class);
    }

    @Override
    public EchantillonDto updateEchantillon(EchantillonDto echantillonDto) {
        Echantillon echantillon = modelMapper.map(echantillonDto, Echantillon.class);
        Echantillon updatedEchantillon = echantillonRepository.save(echantillon);
        return modelMapper.map(updatedEchantillon, EchantillonDto.class);
    }

    @Override
    public void deleteEchantillon(long id) {
        echantillonRepository.deleteById(id);
    }
}
