package com.proyecto.productoVenta.dto;

import java.math.BigDecimal;

public record ProductoVentaDTO(
        Long id,
        String nombreVendedor,
        String nombreProducto,
        Integer cantidad,
        BigDecimal precio) {

}
