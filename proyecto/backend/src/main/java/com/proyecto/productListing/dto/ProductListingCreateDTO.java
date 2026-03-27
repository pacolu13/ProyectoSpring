package com.proyecto.productListing.dto;

import java.math.BigDecimal;

import com.proyecto.product.dto.ProductDTO;

public record ProductListingCreateDTO(
        String name,
        String state,
        Integer quantity,
        BigDecimal price,
        ProductDTO product) {

}
