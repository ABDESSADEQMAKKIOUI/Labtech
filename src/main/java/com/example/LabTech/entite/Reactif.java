package com.example.LabTech.entite;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Reactif {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @PositiveOrZero(message = "La quantité doit être un nombre positif ou zéro.")
        private int quantity;

        @NotBlank(message = "Le nom ne peut pas être vide.")
        private String nom;

        @Temporal(TemporalType.DATE)
        @FutureOrPresent(message = "La date d'expiration doit être dans le futur ou présente.")
        private Date date_expiration;

        private String description;

        @OneToMany(mappedBy = "reactif", cascade = CascadeType.ALL)
        @ToString.Exclude
        @JsonIgnore
        @Fetch(FetchMode.SUBSELECT)
        private List<AnalyseReactif> analyseReactifs = new ArrayList<>();

        @OneToMany(mappedBy = "reactif", cascade = CascadeType.ALL)
        @ToString.Exclude
        @JsonIgnore
        @Fetch(FetchMode.SUBSELECT)
        private List<Enorm> enorms = new ArrayList<>();

        @ManyToOne
        @NotNull(message = "Le fournisseur ne peut pas être nul.")
        private Fournisseur fournisseur;



}


