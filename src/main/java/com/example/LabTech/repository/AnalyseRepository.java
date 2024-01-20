package com.example.LabTech.repository;


import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.enums.Status_Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AnalyseRepository extends JpaRepository<Analyse, Long> {

    // Méthode pour récupérer les analyses dans une plage de dates spécifiée avec une requête JPQL
    @Query(value = "SELECT a FROM Analyse a WHERE a.dateDebut BETWEEN :startDate AND :endDate " , nativeQuery = true)
    List<Analyse> findByDateDebutBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    // Méthode pour compter les analyses par statut avec une requête JPQL
    @Query(value = "SELECT COUNT(a) FROM Analyse a group by a.statusAnalyse " , nativeQuery = true)
    long countByStatusAnalyse();

    // Méthode pour compter les analyses par statut avec une requête JPQL
    @Query(value = "SELECT a FROM Analyse a WHERE a.statusAnalyse = :statusAnalyse" , nativeQuery = true)
    List<Analyse> getAnalyseByStatus(@Param("statusAnalyse") Status_Analyse statusAnalyse);


}
