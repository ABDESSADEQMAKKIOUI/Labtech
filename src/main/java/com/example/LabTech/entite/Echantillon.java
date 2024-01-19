package com.example.LabTech.entite;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Patient patient;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_prend;

    @OneToMany(mappedBy = "echantillon", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Materiel> materiels ;

    @OneToMany(mappedBy = "echantillon", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
   private List<Analyse> analyses ;

}

