package com.proyecto.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.DTOs.LoginDTO;
import com.proyecto.DTOs.RegisterDTO;
import com.proyecto.DTOs.TokenResponseDTO;
import com.proyecto.config.ApiRoutes;
import com.proyecto.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiRoutes.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterDTO request) {
        final TokenResponseDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO request) {
        final TokenResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        final TokenResponseDTO response = authService.refreshToken(authHeader);
        return ResponseEntity.ok(response);
    }

}
