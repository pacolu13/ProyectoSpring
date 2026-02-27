package com.proyecto.client.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ClientDTO(
        Long id,
        String name,
        String lastName,
        BigDecimal balance,
        LocalDateTime creationDate) {
}
