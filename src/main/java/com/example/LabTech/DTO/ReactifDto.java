package com.example.LabTech.DTO;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Reactif}
 */
@Value
public class ReactifDto implements Serializable {
    int quantity;
    String nom;
    Date date_expiration;
}