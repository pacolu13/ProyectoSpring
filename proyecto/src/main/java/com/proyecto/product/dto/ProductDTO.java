package com.proyecto.product.dto;

import java.time.LocalDateTime;

public record ProductDTO(
        Long id,
        String name,
        String description,
        String brand,
        String category,
        String image,
        Boolean active,
        LocalDateTime creationDate) {
}
