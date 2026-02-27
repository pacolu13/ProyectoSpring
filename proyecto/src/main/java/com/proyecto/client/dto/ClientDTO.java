package com.proyecto.client.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClientDTO(
        UUID id,
        String userName,
        BigDecimal balance,
        LocalDateTime creationDate) {
}
