package com.proyecto.vendedor.dto;

import java.time.LocalDateTime;

public record VendedorDTO(
        Long id,
        String nombre,
        String email,
        String cuit,
        Boolean activo,
        LocalDateTime fechaCreacion) {

}
