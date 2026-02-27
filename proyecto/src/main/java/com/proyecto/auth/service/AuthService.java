package com.proyecto.auth.service;

import org.springframework.stereotype.Service;

import com.proyecto.auth.dto.LoginDTO;
import com.proyecto.auth.dto.RegisterDTO;
import com.proyecto.auth.dto.TokenResponseDTO;
import com.proyecto.auth.repository.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenRepository tokenRepository;

    public TokenResponseDTO register(RegisterDTO request){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    public TokenResponseDTO login(LoginDTO request){

        throw new UnsupportedOperationException("Unimplemented method 'login'");

    }

    public TokenResponseDTO refreshToken(TokenResponseDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }
}
