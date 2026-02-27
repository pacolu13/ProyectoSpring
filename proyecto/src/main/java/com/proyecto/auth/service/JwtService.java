package com.proyecto.auth.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.proyecto.client.entity.Client;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    public String generateToken(Client client) {
        return buildToken(client, jwtExpiration);
    }

    public String generateRefreshToken(Client client) {
        return buildToken(client, refreshExpiration);
    }

    private String buildToken(final Client client, final long expiration) {
        return Jwts.builder()
                .id(client.getId().toString())
                .subject(client.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
