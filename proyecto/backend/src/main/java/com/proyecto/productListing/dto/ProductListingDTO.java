package com.proyecto.productListing.dto;

import java.math.BigDecimal;

public record ProductListingDTO(
        Long id,
        String sellerName,
        String productName,
        Integer quantity,
        BigDecimal price) {

}
