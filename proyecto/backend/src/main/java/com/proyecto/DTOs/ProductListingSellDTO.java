package com.proyecto.DTOs;

import java.math.BigDecimal;

public record ProductListingSellDTO(
        Long id,
        ProductDTO product,
        Integer quantity,
        BigDecimal price,
        String state
) {

}
