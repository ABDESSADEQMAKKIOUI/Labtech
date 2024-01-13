package com.example.LabTech.repository;

import com.example.LabTech.entite.Echantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EchantillonRepository extends JpaRepository<Echantillon, Long> {
    @Query("SELECT e FROM echantillon e WHERE e.datePrend BETWEEN :startDate AND :endDate")
    List<Echantillon> findByDatePrendBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}

