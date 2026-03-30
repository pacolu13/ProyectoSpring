package com.proyecto.auth.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.auth.dto.LoginDTO;
import com.proyecto.auth.dto.RegisterDTO;
import com.proyecto.auth.dto.TokenResponseDTO;
import com.proyecto.auth.entity.Token;
import com.proyecto.auth.repository.TokenRepository;
import com.proyecto.cart.entity.Cart;
import com.proyecto.rol.entity.Rol;
import com.proyecto.rol.repository.RolRepository;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class AuthService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponseDTO register(RegisterDTO request) {
        var client = createUser(request);
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
                .orElseThrow(() -> new RuntimeException("User not found"));
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
            throw new RuntimeException("Invalid token");
        }

        final String refreshToken = authHeader.substring(7);
        var userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail == null) {
            throw new RuntimeException("Invalid token");
        }

        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new RuntimeException("Invalid token");
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
