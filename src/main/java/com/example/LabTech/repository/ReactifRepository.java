package com.example.LabTech.repository;

import com.example.LabTech.entite.Reactif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReactifRepository extends JpaRepository<Reactif, Long> {
    @Query(value = "SELECT r FROM Reactif r WHERE r.dateExpiration < CURRENT_DATE" , nativeQuery = true)
    List<Reactif> findByDateExpirationBeforeNow();

}

