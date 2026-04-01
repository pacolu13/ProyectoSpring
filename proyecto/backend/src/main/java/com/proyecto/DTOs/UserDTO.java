package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.proyecto.models.Rol;

public record UserDTO(
        String id,
        String username,
        String email,
        LocalDateTime creationDate,
        Boolean active,
        BigDecimal balance,
        Rol[] rolesList) {

}
