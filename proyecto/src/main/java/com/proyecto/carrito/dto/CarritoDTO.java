package com.proyecto.carrito.dto;

import java.util.List;

import com.proyecto.productoCarrito.dto.ProductoCarritoDTO;

public record CarritoDTO(
                Long id,
                Long clienteId,
                List<ProductoCarritoDTO> productosCarrito) {

}
