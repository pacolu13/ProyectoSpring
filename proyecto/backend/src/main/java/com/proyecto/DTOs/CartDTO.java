package com.proyecto.DTOs;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(
        Long id,
        List<CartProductDTO> products,
        BigDecimal total) {

}
