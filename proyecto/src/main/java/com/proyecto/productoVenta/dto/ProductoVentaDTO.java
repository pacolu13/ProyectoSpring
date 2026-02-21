package com.proyecto.productoVenta.dto;

import java.math.BigDecimal;

public record ProductoVentaDTO(
        Long id,
        Long vendedorId,
        Long productoId,
        Integer cantidad,
        BigDecimal precio) {

}
