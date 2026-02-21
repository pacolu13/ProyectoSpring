package com.proyecto.productoCarrito.dto;

public record ProductoCarritoDTO(
        Long id,
        Long carritoId,
        Long productoId,
        Integer cantidad) {

}
