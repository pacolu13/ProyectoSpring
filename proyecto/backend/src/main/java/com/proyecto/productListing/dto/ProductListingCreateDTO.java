package com.proyecto.productListing.dto;

import java.math.BigDecimal;

import com.proyecto.product.dto.ProductDTO;

public record ProductListingCreateDTO(
        String title,
        BigDecimal price,
        String state,
        Integer stock,
        ProductDTO product) {

}
