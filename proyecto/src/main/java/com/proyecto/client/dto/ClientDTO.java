package com.proyecto.client.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClientDTO(
        UUID id,
        String name,
        String lastName,
        BigDecimal balance,
        LocalDateTime creationDate) {
}
