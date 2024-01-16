package com.example.LabTech.DTO;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Fournisseur}
 */
@Value
public class FournisseurDto implements Serializable {
    String nom;
    List<ReactifDto> reactifs;

    /**
     * DTO for {@link com.example.LabTech.entite.Reactif}
     */
    @Value
    public static class ReactifDto implements Serializable {
        long id;
        int quantity;
        String nom;
        Date date_expiration;
        String description;
    }
}