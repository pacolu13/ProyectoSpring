package com.proyecto.carrito.dto;

public record AgregarProductoCarritoDTO(
        Long clienteId,
        Long productoVentaId,
        Integer cantidad) {

}
