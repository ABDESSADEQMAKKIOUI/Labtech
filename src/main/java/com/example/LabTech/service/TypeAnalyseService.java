package com.example.LabTech.service;

import com.example.LabTech.DTO.Type_AnalyseDto;
import com.example.LabTech.Exception.TypeAnalyseNotFoundException;
import com.example.LabTech.entite.Type_Analyse;
import com.example.LabTech.repository.TypeAnalyseRepository;
import com.example.LabTech.service.interfaces.ITypeAnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Type_AnalyseDto> getAllttypeAnalyse() {
        return null;
    }

    @Override
    public Optional<Type_AnalyseDto> gettypeAnalyseById(long id) {
        Optional<Type_Analyse> typeAnalyse = typeAnalyseRepository.findById(id);

        if (typeAnalyse.isPresent()) {
            return typeAnalyse.map(this::convertToDto);
        } else {
            throw new TypeAnalyseNotFoundException("Type d'analyse non trouvé avec l'ID : " + id);
        }
    }

    @Override
    public Type_AnalyseDto addtypeAnalyse(Type_AnalyseDto typeAnalyseDTO) {
        Type_Analyse typeAnalyse = convertToEntity(typeAnalyseDTO);
        return convertToDto(typeAnalyseRepository.save(typeAnalyse));
    }

    @Override
    public Type_AnalyseDto updatetypeAnalyse(Type_AnalyseDto typeAnalyseDTO) {
        Type_Analyse typeAnalyse = convertToEntity(typeAnalyseDTO);
        return convertToDto(typeAnalyseRepository.save(typeAnalyse));
    }

    @Override
    public void deletetypeAnalyse(long id) {
        typeAnalyseRepository.deleteById(id);
    }

    // Helper methods for mapping
    private Type_AnalyseDto convertToDto(Type_Analyse typeAnalyse) {
        return modelMapper.map(typeAnalyse, Type_AnalyseDto.class);
    }

    private Type_Analyse convertToEntity(Type_AnalyseDto typeAnalyseDTO) {
        return modelMapper.map(typeAnalyseDTO, Type_Analyse.class);
    }
}
