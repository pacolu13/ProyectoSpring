package com.proyecto.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.auth.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
    