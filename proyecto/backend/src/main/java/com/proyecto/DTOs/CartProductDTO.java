package com.proyecto.DTOs;

import java.math.BigDecimal;

public record CartProductDTO(
        Long id,
        Long productListingId,
        String name,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subtotal) {

}
