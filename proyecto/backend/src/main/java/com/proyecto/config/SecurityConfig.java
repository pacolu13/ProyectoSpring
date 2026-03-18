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
                http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
                                .requestMatchers(ApiRoutes.AUTH + ApiRoutes.ALL_STRING).permitAll()
                                .requestMatchers(ApiRoutes.PRODUCTS + ApiRoutes.ALL_STRING).permitAll()
                                .requestMatchers(ApiRoutes.PRODUCTS_LISTING + ApiRoutes.ALL_STRING).permitAll()
                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                                                .addLogoutHandler((request, response, authentication) -> {
                                                        final String authHeader = request
                                                                        .getHeader(HttpHeaders.AUTHORIZATION);
                                                        logout(authHeader);
                                                })
                                                .logoutSuccessHandler((request, response,
                                                                authentication) -> SecurityContextHolder
                                                                                .clearContext()));
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
