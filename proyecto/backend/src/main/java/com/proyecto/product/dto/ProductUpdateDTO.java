package com.proyecto.product.dto;

public record ProductUpdateDTO (
    Long id,
    String name,
    String description,
    String brand,
    String category,
    String image
){}
