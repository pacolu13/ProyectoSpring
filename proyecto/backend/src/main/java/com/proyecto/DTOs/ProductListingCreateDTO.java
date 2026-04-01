package com.proyecto.DTOs;

import java.math.BigDecimal;

public record ProductListingCreateDTO(
        String title,
        BigDecimal price,
        String state,
        Integer quantity,
        ProductDTO product) {

}
