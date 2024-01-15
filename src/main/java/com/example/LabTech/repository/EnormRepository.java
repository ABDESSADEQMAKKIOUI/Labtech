package com.example.LabTech.repository;

import com.example.LabTech.entite.Enorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnormRepository extends JpaRepository<Enorm, Long> {

    @Query(value = "SELECT e FROM Enorm e JOIN e.typeAnalyse t WHERE t.id = :typeAnalyseId" , nativeQuery = true)
    List<Enorm> findByTypeAnalyseId(@Param("typeAnalyseId") Long typeAnalyseId);
}
