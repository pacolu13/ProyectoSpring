package com.proyecto.productListing.dto;

import java.math.BigDecimal;

public record ProductListingDTO(
        Long id,
        String nameSeller,
        String nameProduct,
        Integer quantity,
        BigDecimal price) {

}
