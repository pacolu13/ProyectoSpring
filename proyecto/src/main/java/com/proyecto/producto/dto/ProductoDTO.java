package com.proyecto.producto.dto;

import java.time.LocalDateTime;

public record ProductoDTO(
        String nombre,
        String descripcion,
        String marca,
        String categoria,
        String imagen,
        Boolean activo,
        LocalDateTime fechaCreacion) {
}
