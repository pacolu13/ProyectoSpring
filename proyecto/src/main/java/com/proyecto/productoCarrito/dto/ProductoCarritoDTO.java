package com.proyecto.productoCarrito.dto;

public record ProductoCarritoDTO(
        Long id,
        Long productoVentaId,
        String nombre,
        Integer cantidad) {

}
