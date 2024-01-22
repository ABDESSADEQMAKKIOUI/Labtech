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

public interface EchantillonRepository extends JpaRepository<Echantillon, Long> {
    @Query(value = "SELECT e FROM echantillon e WHERE e.datePrend BETWEEN :startDate AND :endDate" , nativeQuery = true)
    List<Echantillon> findByDatePrendBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT a FROM Echantillon a WHERE a.patient = :patient")
    List<Echantillon> getEchantillonByPatient(@Param("patient") Patient patient);

}

