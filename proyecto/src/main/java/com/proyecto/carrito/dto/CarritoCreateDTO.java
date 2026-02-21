package com.proyecto.carrito.dto;

import java.util.List;

import com.proyecto.productoCarrito.dto.ProductoCarritoDTO;

public record CarritoCreateDTO(
                Long id,
                Long clienteId,
                List<ProductoCarritoDTO> productosCarrito) {

}
