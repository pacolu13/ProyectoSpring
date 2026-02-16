package com.proyecto.cliente.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ClienteDTO(
        Long id,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String contrasenia,
        BigDecimal saldo,
        Boolean activo,
        LocalDateTime fechaRegistro
){

   
}

