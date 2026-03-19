package com.proyecto.cart.dto;

import java.util.List;

import com.proyecto.cartProduct.dto.CartProductDTO;

public record CartUpdateDTO(
                Long id,
                List<CartProductDTO> cartProductsList) {

}
