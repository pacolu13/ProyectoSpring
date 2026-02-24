package com.proyecto.detalleCompra.dto;

import java.math.BigDecimal;

public record DetalleCompraDTO(
    Long id,
    String nombreProducto,
    Integer cantidad,
    BigDecimal precioUnitario
) {

}
