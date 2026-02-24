package com.proyecto.compra.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.detalleCompra.dto.DetalleCompraDTO;

public record CompraDTO(
        String nombreCliente,
        String apellidoCliente,
        LocalDateTime fecha,
        List<DetalleCompraDTO> detalles,
        BigDecimal total) {

}
