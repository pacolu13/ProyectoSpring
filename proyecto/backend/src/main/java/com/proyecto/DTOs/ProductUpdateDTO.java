package com.proyecto.DTOs;

public record ProductUpdateDTO (
    Long id,
    String name,
    String description,
    String brand,
    String category,
    String image
){}
