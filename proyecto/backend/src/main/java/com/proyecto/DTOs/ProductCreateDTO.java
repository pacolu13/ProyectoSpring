package com.proyecto.DTOs;

public record ProductCreateDTO(
        String name,
        String description,
        String brand,
        String category,
        String image) {
}
