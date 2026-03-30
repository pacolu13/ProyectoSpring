package com.proyecto.auth.dto;

import java.util.List;

import com.proyecto.rol.entity.Rol;

public record RegisterDTO(
    String username,
    String password,
    String email,
    List<Rol> roles
) {

}
