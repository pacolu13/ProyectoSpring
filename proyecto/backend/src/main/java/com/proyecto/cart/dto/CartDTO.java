package com.proyecto.cart.dto;

import java.util.List;
import java.util.UUID;

import com.proyecto.cartProduct.dto.CartProductDTO;

public record CartDTO(
                Long id,
                UUID userId,
                List<CartProductDTO> cartProductsList) {

}
