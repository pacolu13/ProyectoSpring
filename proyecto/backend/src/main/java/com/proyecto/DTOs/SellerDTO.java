package com.proyecto.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record SellerDTO(
        UUID id,
        String username,
        String email,
        Integer totalSales,
        Float rating,
        LocalDateTime creationDate) {
}
