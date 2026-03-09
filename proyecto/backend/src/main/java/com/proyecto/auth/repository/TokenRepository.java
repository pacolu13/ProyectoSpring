package com.proyecto.auth.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.auth.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidIsFalseOrRevokedIsFalseTokenByUserId(UUID userId);
    Optional<Token> findByToken(String token);
}
    