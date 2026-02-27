package com.proyecto.product.dto;

public record ProductCreateDTO(
        String name,
        String description,
        String brand,
        String category,
        String image) {
}
