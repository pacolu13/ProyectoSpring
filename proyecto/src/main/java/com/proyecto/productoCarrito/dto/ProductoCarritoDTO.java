package com.proyecto.productoCarrito.dto;

public record ProductoCarritoDTO(
        Long id,
        Long CarritoId,
        Long ProductoId,
        Integer cantidad) {

}
