package com.proyecto.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidIsFalseOrRevokedIsFalseTokenByUserId(UUID userId);
    Optional<Token> findByToken(String token);
}
    