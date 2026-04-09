package com.proyecto.DTOs;

import java.math.BigDecimal;

import com.proyecto.models.StateProduct;

public record ProductListingUpdateDTO(
        String title,
        String description,
        Integer stock,
        StateProduct state,
        BigDecimal price,
        ProductDTO product) {
}
