package com.proyecto.cart.dto;

import java.math.BigDecimal;
import java.util.List;

import com.proyecto.cartProduct.dto.CartProductDTO;

public record CartDTO(
                Long id,
                List<CartProductDTO> cartProductsList,
                BigDecimal total) {

}
