package com.example.LabTech.service;

import com.example.LabTech.DTO.FournisseurDto;
import com.example.LabTech.entite.Fournisseur;
import com.example.LabTech.repository.FournisseurRepository;
import com.example.LabTech.service.interfaces.IFournisseurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FournisseurService implements IFournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Méthodes de service pour la logique métier liée à Fournisseur
    @Override
    public List<FournisseurDto> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        return fournisseurs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FournisseurDto> getFournisseurById(long id) {
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return fournisseur.map(this::convertToDto);
    }

    @Override
    public FournisseurDto addFournisseur(FournisseurDto fournisseurDTO) {
        Fournisseur fournisseur = convertToEntity(fournisseurDTO);
        return convertToDto(fournisseurRepository.save(fournisseur));
    }

    @Override
    public FournisseurDto updateFournisseur(FournisseurDto fournisseurDTO) {
        Fournisseur fournisseur = convertToEntity(fournisseurDTO);
        return convertToDto(fournisseurRepository.save(fournisseur));
    }

    @Override
    public void deleteFournisseur(long id) {
        fournisseurRepository.deleteById(id);
    }

    // Helper methods for mapping
    private FournisseurDto convertToDto(Fournisseur fournisseur) {
        return modelMapper.map(fournisseur, FournisseurDto.class);
    }

    private Fournisseur convertToEntity(FournisseurDto fournisseurDTO) {
        return modelMapper.map(fournisseurDTO, Fournisseur.class);
    }
}
