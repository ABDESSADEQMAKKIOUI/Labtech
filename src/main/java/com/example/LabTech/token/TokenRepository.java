package com.example.LabTech.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query()
  List<Token> findAllValidTokenByUser(Long id);

  Optional<Token> findByToken(String token);
}
