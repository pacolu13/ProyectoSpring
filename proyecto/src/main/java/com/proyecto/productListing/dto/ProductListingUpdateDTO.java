package com.proyecto.productListing.dto;

import java.math.BigDecimal;

public record ProductListingUpdateDTO(
        Long sellerId,
        Long productId,
        Integer quantity,
        BigDecimal price) {

}
