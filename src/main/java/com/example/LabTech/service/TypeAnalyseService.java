package com.example.LabTech.service;

import com.example.LabTech.DTO.TypeAnalyseDto;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.TypeAnalyseRepository;
import com.example.LabTech.service.interfaces.ITypeAnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeAnalyseService implements ITypeAnalyseService {

    @Autowired
    private TypeAnalyseRepository typeAnalyseRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public List<TypeAnalyseDto> getAllttypeAnalyse() {
//        List<Type_Analyse> typeAnalyses = typeAnalyseRepository.findAll();
//        return typeAnalyses.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<TypeAnalyseDto> getAllttypeAnalyse() {
        return null;
    }

    @Override
    public Optional<TypeAnalyseDto> gettypeAnalyseById(long id) {
        Optional<Type_Analyse> typeAnalyse = typeAnalyseRepository.findById(id);
        return typeAnalyse.map(this::convertToDto);
    }

    @Override
    public TypeAnalyseDto addtypeAnalyse(TypeAnalyseDto typeAnalyseDTO) {
        Type_Analyse typeAnalyse = convertToEntity(typeAnalyseDTO);
        return convertToDto(typeAnalyseRepository.save(typeAnalyse));
    }

    @Override
    public TypeAnalyseDto updatetypeAnalyse(TypeAnalyseDto typeAnalyseDTO) {
        Type_Analyse typeAnalyse = convertToEntity(typeAnalyseDTO);
        return convertToDto(typeAnalyseRepository.save(typeAnalyse));
    }

    @Override
    public void deletetypeAnalyse(long id) {
        typeAnalyseRepository.deleteById(id);
    }

    // Helper methods for mapping
    private TypeAnalyseDto convertToDto(Type_Analyse typeAnalyse) {
        return modelMapper.map(typeAnalyse, TypeAnalyseDto.class);
    }

    private Type_Analyse convertToEntity(TypeAnalyseDto typeAnalyseDTO) {
        return modelMapper.map(typeAnalyseDTO, Type_Analyse.class);
    }
}
