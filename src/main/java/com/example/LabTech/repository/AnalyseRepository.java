package com.example.LabTech.repository;


import com.example.LabTech.entite.Analyse;
import com.example.LabTech.entite.Echantillon;
import com.example.LabTech.entite.Patient;
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
    @Query(value = "SELECT COUNT(a) FROM Analyses a group by a.statusAnalyse " , nativeQuery = true)
    long countByStatusAnalyse();

    // Méthode pour compter les analyses par statut avec une requête JPQL
    @Query("SELECT a FROM Analyse a WHERE a.statusAnalyse = :statusAnalyse")
    List<Analyse> getAnalyseByStatus(@Param("statusAnalyse") Status_Analyse statusAnalyse);
    @Query("SELECT a FROM Analyse a WHERE a.echantillon = :echantllon")
    List<Analyse> getAnalyseByEchantillon(@Param("echantllon") Echantillon echantllon);

    @Query("SELECT\n" +
            "    a.name AS nomAnalyse,\n" +
            "    p.nom AS Nom,\n" +
            "    p.prenom AS Prenom,\n" +
            "    p.tele AS Numero,\n" +
            "    p.adress AS Adresse,\n" +
            "    ta.name AS NomTypeAnalyse,\n" +
            "    te.status AS Resultat,\n" +
            "    n.name AS libelleNorme,\n" +
            "    n.plage_normale_max AS maxValueNorme,\n" +
            "    n.plage_normale_min AS minValueNorme,\n" +
            "    n.unite_mesure AS uniteNorme " +
            "FROM Analyse a JOIN a.echantillon e JOIN e.patient p JOIN a.typeAnalyses ta JOIN ta.testAnalyses te " +
            "JOIN te.enorm n WHERE a.id = ?1")
    List<Object[]> getAnalysisReport(@Param("id") Long id);


}
