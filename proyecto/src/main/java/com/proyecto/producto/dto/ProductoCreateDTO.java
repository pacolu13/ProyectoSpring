package com.proyecto.producto.dto;

public record ProductoCreateDTO(
        String nombre,
        String descripcion,
        String marca,
        String categoria,
        String imagen) {
}
