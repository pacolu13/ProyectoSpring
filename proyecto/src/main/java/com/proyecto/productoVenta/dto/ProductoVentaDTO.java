package com.proyecto.productoVenta.dto;

public record ProductoVentaDTO(
    Long id,
    Long clienteId,
    Long productoId,
    Long cantidad,
    Long precio
) {

}
