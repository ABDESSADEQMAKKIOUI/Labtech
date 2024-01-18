package com.example.LabTech.service.interfaces;

import com.example.LabTech.DTO.TechnitienDto;
import com.example.LabTech.entite.Technitien;

import java.util.List;
import java.util.Optional;

public interface ITechnitientService {

    List<TechnitienDto>  getAlltechnitiens();

    Optional<TechnitienDto> gettechnitienById(long id);

    TechnitienDto addtechnitien(TechnitienDto technitien);

    TechnitienDto updatetechnitien(TechnitienDto technitien);

    void deletetechnitien(long id);
}