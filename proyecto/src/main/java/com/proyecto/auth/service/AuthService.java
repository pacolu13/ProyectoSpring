package com.proyecto.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.auth.dto.LoginDTO;
import com.proyecto.auth.dto.RegisterDTO;
import com.proyecto.auth.dto.TokenResponseDTO;
import com.proyecto.auth.entity.Token;
import com.proyecto.auth.repository.TokenRepository;
import com.proyecto.client.entity.Client;
import com.proyecto.client.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenRepository tokenRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenResponseDTO register(RegisterDTO request) {
        var client = new Client();
        client.setUserName(request.username());
        client.setEmail(request.email());
        client.setPassword(passwordEncoder.encode(request.password()));

        var clientSaved = clientRepository.save(client);
        var token = jwtService.generateToken(clientSaved);
        var refreshToken = jwtService.generateRefreshToken(clientSaved);
        saveUserToken(clientSaved, token);
        return new TokenResponseDTO(token, refreshToken);
    }

    public TokenResponseDTO login(LoginDTO request) {

        throw new UnsupportedOperationException("Unimplemented method 'login'");

    }

    public TokenResponseDTO refreshToken(String authHeader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

    private void saveUserToken(Client client, String token) {
        var tokenEntity = Token.builder()
                .client(client)
                .token(token)
                .type(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(tokenEntity);
    }
}
