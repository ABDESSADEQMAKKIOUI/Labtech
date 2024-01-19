package com.example.LabTech.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.example.LabTech.entite.Fournisseur}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDto implements Serializable {
    private long id;

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