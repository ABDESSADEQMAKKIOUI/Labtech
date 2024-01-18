package com.example.LabTech.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.LabTech.entite.Reactif}
 */
@NoArgsConstructor
@Data
public class ReactifDto implements Serializable {
    private long id;

    int quantity;
    String nom;
    Date date_expiration;
}