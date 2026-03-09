package com.proyecto.productListing.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductListingCreateDTO(
        UUID sellerId,
        Long productId,
        Integer quantity,
        BigDecimal price) {

}
