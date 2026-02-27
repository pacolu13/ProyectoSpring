package com.proyecto.cart.dto;

import java.util.List;
import java.util.UUID;

import com.proyecto.cartProduct.dto.CartProductDTO;

public record CartUpdateDTO(
                Long id,
                UUID clientId,
                List<CartProductDTO> cartProductsList) {

}
