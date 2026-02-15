package com.proyecto.cliente.dto;

public record ClienteUpdateDTO(
    String nombre,
    String apellido,
    String telefono,
    String email,
    String contrasenia
) {}