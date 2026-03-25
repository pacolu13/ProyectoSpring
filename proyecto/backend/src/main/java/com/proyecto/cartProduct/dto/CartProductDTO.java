package com.proyecto.cartProduct.dto;

import java.math.BigDecimal;

public record CartProductDTO(
                Long id,
                Long productListingId,
                String name,
                Integer quantity,
                BigDecimal subtotal) {

}
