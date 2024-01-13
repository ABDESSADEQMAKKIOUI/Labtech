package com.example.LabTech.DTO;

import com.example.demo.DTO.enums.Periode_RapportDTO;
import com.example.demo.DTO.enums.Type_RapportDTO;
import lombok.Data;


@Data
public class RapportStatistiqueDTO {

    private long id;
    private Type_RapportDTO typeRapport;
    private Periode_RapportDTO periodeRapport;
}

