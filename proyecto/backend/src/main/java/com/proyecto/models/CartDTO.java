package com.proyecto.models;

import java.math.BigDecimal;
import java.util.List;

import com.proyecto.DTOs.CartProductDTO;

public record CartDTO(
                Long id,
                List<CartProductDTO> cartProductsList,
                BigDecimal total) {

}
