package com.proyecto.DTOs;

import java.math.BigDecimal;

import com.proyecto.models.StateProduct;

public record ProductListingCreateDTO(
        String title,
        String description,
        BigDecimal price,
        Integer stock,
        StateProduct state,
        ProductDTO product) {
}
