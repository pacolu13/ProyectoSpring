package com.proyecto.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyecto.auth.entity.Token;
import com.proyecto.auth.repository.TokenRepository;
import com.proyecto.auth.service.JwtService;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth/")) {
            filterChain.doFilter(request, response);
            System.out.println("Ruta de autenticación detectada, omitiendo filtro JWT");
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("No se encontró un token Bearer en el encabezado Authorization, omitiendo filtro JWT");
            return;
        }

        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);
        if(userEmail == null ||SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            System.out.println("No se pudo extraer el email del token o ya existe una autenticación en el contexto, omitiendo filtro JWT");
            return;
        }

        final Token token = tokenRepository.findByToken(jwt)
                .orElse(null);
        if(token == null || token.getExpired() || token.getRevoked()) {
            filterChain.doFilter(request, response);
            System.out.println("Token no encontrado en la base de datos o es inválido (expirado o revocado), omitiendo filtro JWT");
            return;
        }                

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        final Optional<User> user = userRepository.findByEmail(userEmail);
        if(user.isEmpty()) {
            filterChain.doFilter(request, response);
            System.out.println("Usuario no encontrado en la base de datos, omitiendo filtro JWT");
            return;
        }

        final boolean isTokenValid = jwtService.isTokenValid(jwt, user.get());
        if (!isTokenValid) {
            filterChain.doFilter(request, response);
            System.out.println("Token inválido, omitiendo filtro JWT");
            return;
        }

        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken); // Establece la autenticación en el contexto de seguridad
        filterChain.doFilter(request, response);
    }
}
