package com.proyecto.cartProduct.dto;

import java.math.BigDecimal;

public record CartProductDTO(
                Long id,
                Long productListingId,
                BigDecimal unitPrice,
                String name,
                Integer quantity,
                BigDecimal subtotal) {

}
