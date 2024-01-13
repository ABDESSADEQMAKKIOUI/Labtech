package com.example.LabTech.repository;

import com.example.LabTech.entite.Reactif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReactifRepository extends JpaRepository<Reactif, Long> {
    @Query("SELECT r FROM reactif r WHERE r.dateExpiration < CURRENT_DATE")
    List<Reactif> findByDateExpirationBeforeNow();

}

