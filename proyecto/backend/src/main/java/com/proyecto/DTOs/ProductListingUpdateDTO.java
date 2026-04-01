package com.proyecto.DTOs;

import java.math.BigDecimal;

public record ProductListingUpdateDTO(
        String title,
        String description,
        Integer stock,
        String state,
        BigDecimal price,
        ProductDTO product) {
}
