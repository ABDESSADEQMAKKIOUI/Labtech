package com.example.LabTech.entite;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Data

public class Patient extends Personne {
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Collection<Echantillon> echantillons = new ArrayList<>();
}

