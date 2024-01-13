package com.example.LabTech.entite;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Technitien extends Personne{
    @OneToMany(mappedBy = "technitien", cascade = CascadeType.ALL)
    private List<Test_analyse> testAnalyses ;
    @OneToOne()
    private Compte compte ;

}
