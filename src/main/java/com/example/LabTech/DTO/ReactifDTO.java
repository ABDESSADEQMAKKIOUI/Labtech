package com.example.LabTech.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReactifDTO {

    private long id;
    private int quantity;
    private String nom;
    private Date date_expiration;
    private String description;
    private FournisseurDTO fournisseur;
}
