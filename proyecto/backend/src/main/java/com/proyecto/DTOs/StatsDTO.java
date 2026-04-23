package com.proyecto.DTOs;

import lombok.Builder;

@Builder
public record StatsDTO(
        Long cantUsers,
        Long cantProducts,
        Long cantOrders) {
}
