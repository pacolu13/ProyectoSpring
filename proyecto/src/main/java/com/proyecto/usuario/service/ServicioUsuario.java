package com.proyecto.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.usuario.repository.RepoUsuario;

@Service
// Servicio que implementa UserDetailsService para cargar los detalles del usuario desde la base de datos
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private RepoUsuario repoUsuario;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario)
            throws UsernameNotFoundException {
        var usuario = repoUsuario.findByUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(usuario.getUsuario(), usuario.getPassword(),
        //Convierte un usuario en el formato que necesita Spring Security, 
        //signándole un rol basado en el campo "rol" del usuario
        List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));
    }
}
