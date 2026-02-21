package com.proyecto.carrito.dto;

import java.util.List;

import com.proyecto.productoCarrito.dto.ProductoCarritoDTO;

public record CarritoUpdateDTO(
                Long id,
                Long clienteId,
                List<ProductoCarritoDTO> productosCarrito) {

}
