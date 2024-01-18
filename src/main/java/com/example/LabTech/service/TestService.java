package com.example.LabTech.service;

import com.example.LabTech.DTO.TestAnalyseDto;
import com.example.LabTech.DTO.TypeAnalyseDto;
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
    public List<TestAnalyseDto> getAllTests() {
        List<Test_analyse> tests = testRepository.findAll();
        return tests.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TestAnalyseDto> getTestById(long id) {
        Optional<Test_analyse> testAnalyse = testRepository.findById(id);
        return testAnalyse.map(this::convertToDto);
    }

    @Override
    public TestAnalyseDto addTest(TestAnalyseDto testAnalyseDTO) {
        Test_analyse testAnalyse = convertToEntity(testAnalyseDTO);
        return convertToDto(testRepository.save(testAnalyse));
    }

    @Override
    public TestAnalyseDto updateTest(TestAnalyseDto testAnalyseDTO) {
        Test_analyse testAnalyse = convertToEntity(testAnalyseDTO);
        return convertToDto(testRepository.save(testAnalyse));
    }

    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<TestAnalyseDto> getTestsByAnalyse(Analyse analyse) {
        List<Test_analyse> tests = testRepository.findByAnalyse(analyse);
        return tests.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    // Helper methods for mapping
    private TestAnalyseDto convertToDto(Test_analyse testAnalyse) {
        return modelMapper.map(testAnalyse, TestAnalyseDto.class);
    }

    private Test_analyse convertToEntity(TestAnalyseDto testAnalyseDTO) {
        return modelMapper.map(testAnalyseDTO, Test_analyse.class);
    }
}
