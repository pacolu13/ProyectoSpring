package com.proyecto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity // Habilita la seguridad web en la aplicación
public class SecurityConfig {

    // Inyectamos el servicio de usuario para cargar los detalles del usuario
    /*
     * private final ServicioUsuario servicioUsuario;
     * 
     * public SecurityConfig(ServicioUsuario servicioUsuario) {
     * this.servicioUsuario = servicioUsuario;
     * }
     */
    public SecurityConfig() {

    }

    @Bean
    public PasswordEncoder codificaPass() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Configura el AuthenticationManager para manejar la autenticación de usuarios
    public AuthenticationManager autenticacion(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager(); // Devuelve el AuthenticationManager configurado por Spring
                                                      // Security
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
