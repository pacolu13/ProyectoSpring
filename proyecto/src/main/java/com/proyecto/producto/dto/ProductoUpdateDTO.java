package com.proyecto.producto.dto;

public record ProductoUpdateDTO (
    Long id,
    String nombre,
    String descripcion,
    String marca,
    String categoria,
    String imagen
){}
