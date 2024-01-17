package com.example.LabTech.service;

import com.example.LabTech.DTO.CompteDto;
import com.example.LabTech.entite.Compte;
import com.example.LabTech.repository.CompteRepository;
import com.example.LabTech.service.interfaces.ICompteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompteService implements ICompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ModelMapper modelMapper; // Add ModelMapper

    // Méthodes de service pour la logique métier liée à Analyse
    @Override
    public List<CompteDto> getAllComptes() {
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream()
                .map(compte -> modelMapper.map(compte, CompteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompteDto> getCompteById(long id) {
        Optional<Compte> compte = compteRepository.findById(id);
        return compte.map(value -> modelMapper.map(value, CompteDto.class));
    }

    @Override
    public CompteDto addCompte(CompteDto compteDto) {
        Compte compte = modelMapper.map(compteDto, Compte.class);
        Compte savedCompte = compteRepository.save(compte);
        return modelMapper.map(savedCompte, CompteDto.class);
    }

    @Override
    public CompteDto updateCompte(CompteDto compteDto) {
        Compte compte = modelMapper.map(compteDto, Compte.class);
        Compte updatedCompte = compteRepository.save(compte);
        return modelMapper.map(updatedCompte, CompteDto.class);
    }

    @Override
    public void deleteCompte(long id) {
        compteRepository.deleteById(id);
    }
}
