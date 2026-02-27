package com.proyecto.cart.dto;

import java.util.List;

import com.proyecto.cartProduct.dto.CartProductDTO;

public record CartUpdateDTO(
                Long id,
                Long clientId,
                List<CartProductDTO> cartProductsList) {

}
