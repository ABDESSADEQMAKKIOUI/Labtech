package com.example.LabTech.service;

import com.example.LabTech.DTO.Test_analyseDto;
import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;
import com.example.LabTech.repository.TestRepository;
import com.example.LabTech.service.interfaces.ITestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Test_analyseDto> getAllTests() {
        List<Test_analyse> tests = testRepository.findAll();
        return tests.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Test_analyseDto> getTestById(long id) {
        Optional<Test_analyse> testAnalyse = testRepository.findById(id);
        return testAnalyse.map(this::convertToDto);
    }

    @Override
    public Test_analyseDto addTest(Test_analyseDto testAnalyseDTO) {
        Test_analyse testAnalyse = convertToEntity(testAnalyseDTO);
        return convertToDto(testRepository.save(testAnalyse));
    }

    @Override
    public Test_analyseDto updateTest(Test_analyseDto testAnalyseDTO) {
        Test_analyse testAnalyse = convertToEntity(testAnalyseDTO);
        return convertToDto(testRepository.save(testAnalyse));
    }

    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<Test_analyseDto> getTestsByAnalyse(Analyse analyse) {
        return null;
    }

//    @Override
//    public List<TestAnalyseDto> getTestsByAnalyse(Analyse analyse) {
//        List<Test_analyse> tests = testRepository.findByAnalyse(analyse);
//        return tests.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//
//    }

    // Helper methods for mapping
    private Test_analyseDto convertToDto(Test_analyse testAnalyse) {
        return modelMapper.map(testAnalyse, Test_analyseDto.class);
    }

    private Test_analyse convertToEntity(Test_analyseDto testAnalyseDTO) {
        return modelMapper.map(testAnalyseDTO, Test_analyse.class);
    }
}
