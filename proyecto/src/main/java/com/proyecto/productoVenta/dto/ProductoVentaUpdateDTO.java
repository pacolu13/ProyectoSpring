package com.proyecto.productoVenta.dto;

import java.math.BigDecimal;

public record ProductoVentaUpdateDTO(
        Long vendedorId,
        Long productoId,
        Integer cantidad,
        BigDecimal precio) {

}
