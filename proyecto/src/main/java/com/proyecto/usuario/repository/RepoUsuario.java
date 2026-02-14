package com.proyecto.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.usuario.entity.Usuario;

public interface RepoUsuario extends JpaRepository<Usuario, Long>{
    Optional<Usuario>findByUsuario(String usuario);

}
