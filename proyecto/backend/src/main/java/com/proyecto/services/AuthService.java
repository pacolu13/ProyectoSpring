package com.proyecto.services;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.DTOs.LoginDTO;
import com.proyecto.DTOs.RegisterDTO;
import com.proyecto.DTOs.TokenResponseDTO;
import com.proyecto.config.ExceptionFactory;
import com.proyecto.models.Cart;
import com.proyecto.models.Rol;
import com.proyecto.models.Token;
import com.proyecto.models.User;
import com.proyecto.repositories.RolRepository;
import com.proyecto.repositories.TokenRepository;
import com.proyecto.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class AuthService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponseDTO register(RegisterDTO request) {

        System.out.println(request);
        var client = createUser(request);

        List<Rol> roles = rolRepository.findAllByNameIn(request.roles());
        client.setRoles(roles);

        var clientSaved = userRepository.save(client);
        var token = jwtService.generateToken(clientSaved);
        var refreshToken = jwtService.generateRefreshToken(clientSaved);

        saveUserToken(clientSaved, token);
        return new TokenResponseDTO(token, refreshToken);
    }

    public TokenResponseDTO login(LoginDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return new TokenResponseDTO(token, refreshToken);
    }

    private User createUser(RegisterDTO request) {
        var client = User.builder().build();
        client.setUsername(request.username());
        client.setEmail(request.email());
        client.setPassword(passwordEncoder.encode(request.password()));

        List<Rol> roles = rolRepository.findAllByNameIn(request.roles());
        client.setRoles(roles);

        Cart cart = new Cart();
        client.setCart(cart);
        cart.setUser(client);

        return client;
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidIsFalseOrRevokedIsFalseTokenByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public TokenResponseDTO refreshToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw ExceptionFactory.createTokenInvalidException();
        }

        final String refreshToken = authHeader.substring(7);
        var userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail == null) {
            throw ExceptionFactory.createTokenInvalidException();
        }

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> ExceptionFactory.createUserNotFoundException());

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw ExceptionFactory.createTokenInvalidException();
        }

        final String accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponseDTO(accessToken, refreshToken);
    }

    private void saveUserToken(User user, String token) {
        var tokenEntity = Token.builder()
                .user(user)
                .token(token)
                .type(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(tokenEntity);
    }
}
