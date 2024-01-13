package com.example.LabTech.DTO;

import com.example.demo.entite.Patient;
import lombok.Data;

import java.util.Date;

@Data
public class EchantillonDTO {

    private long id;
    private Patient patient;
    private Date date_prend;
}
