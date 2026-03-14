package com.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyecto.auth.repository.TokenRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Habilita la seguridad web en la aplicación
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final TokenRepository tokenRepository;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/v1/**").permitAll() // Permite el acceso a las rutas de autenticación sin necesidad de autenticación
                                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
                        )
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider) // Configura el proveedor de autenticación personalizado
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Agrega el filtro de autenticación JWT antes del filtro de autenticación de nombre de usuario y contraseña
                        .logout(logout -> 
                                logout.logoutUrl("/api/v1/auth/logout") // Configura la URL de cierre de sesión
                                        .addLogoutHandler((request, response, authentication) -> {
                                                final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                                                logout(authHeader);
                                        })
                                        .logoutSuccessHandler((request, response, authentication) -> 
                                                SecurityContextHolder.clearContext())
                        );              
                        return http.build();
        }

        private void logout(String authHeader) {
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String jwt = authHeader.substring(7);
                        tokenRepository.findByToken(jwt).ifPresent(token -> {
                                token.setExpired(true);
                                token.setRevoked(true);
                                tokenRepository.save(token);
                        });
                } else {
                        throw new RuntimeException("Invalid Token");
                }
        }
}
