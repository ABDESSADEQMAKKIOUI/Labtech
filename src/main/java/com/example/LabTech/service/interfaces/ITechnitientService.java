package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Technitien;

import java.util.List;
import java.util.Optional;

public interface ITechnitientService {

    List<Technitien>  getAlltechnitiens();

    Optional<Technitien> gettechnitienById(long id);

    Technitien addtechnitien(Technitien technitien);

    Technitien updatetechnitien(Technitien technitien);

    void deletetechnitien(long id);
}