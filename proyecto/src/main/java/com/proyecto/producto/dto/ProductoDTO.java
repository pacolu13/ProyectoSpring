package com.proyecto.producto.dto;

import java.time.LocalDateTime;

public record ProductoDTO(
        Long id,
        String nombre,
        String descripcion,
        String marca,
        String categoria,
        String imagen,
        Boolean activo,
        LocalDateTime fechaCreacion) {
}
