package com.proyecto.cliente.dto;

public record ClienteCreateDTO(
    String nombre,
    String apellido,
    String telefono,
    String email,
    String contrasenia
) {}