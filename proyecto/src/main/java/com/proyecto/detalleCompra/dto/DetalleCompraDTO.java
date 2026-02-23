package com.proyecto.detalleCompra.dto;

public record DetalleCompraDTO(
    Long id,
    Long compraId,
    String nombreProducto,
    Integer cantidad,
    Double precioUnitario
) {

}
