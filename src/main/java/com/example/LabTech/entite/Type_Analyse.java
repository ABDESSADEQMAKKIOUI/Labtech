package com.example.LabTech.entite;


import com.example.LabTech.entite.enums.Type_Analyse_name;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@Data
public class Type_Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @NotBlank(message = "Le nom ne peut pas être vide.")
    private String name ;

    @OneToMany(mappedBy = "typeAnalyse", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Test_analyse> testAnalyses = new ArrayList<>();

    @ManyToOne
    private Analyse analyse ;


}
