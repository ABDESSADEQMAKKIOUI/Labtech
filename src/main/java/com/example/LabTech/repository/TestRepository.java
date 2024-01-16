package com.example.LabTech.repository;

import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Test_analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends JpaRepository<Test_analyse, Long> {

    // Méthode pour récupérer les tests associés à une analyse avec une requête JPQL
    @Query(value = "SELECT t FROM Test_analyse t WHERE t.analyse = :analyse" , nativeQuery = true)
    List<Test_analyse> findByAnalyse(@Param("analyse") Analyse analyse);

}

